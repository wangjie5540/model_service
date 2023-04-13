package com.digitforce.aip.facade;

import com.digitforce.aip.dto.data.PredictResultDTO;
import com.digitforce.aip.dto.data.ServingInstanceDTO;
import com.digitforce.aip.dto.qry.GetPredictResultQry;
import com.digitforce.aip.dto.qry.ServingInstanceGetByIdQry;
import com.digitforce.aip.dto.qry.ServingInstancePageByQry;
import com.digitforce.aip.entity.ServingInstance;
import com.digitforce.aip.enums.ScoreRangeType;
import com.digitforce.aip.mapper.OlapMapper;
import com.digitforce.aip.service.IServingInstanceService;
import com.digitforce.aip.utils.OlapHelper;
import com.digitforce.framework.api.dto.PageView;
import com.digitforce.framework.api.dto.Result;
import com.digitforce.framework.tool.ConvertTool;
import com.digitforce.framework.tool.PageTool;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 服务实例查询接口实现类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 17:08
 */
@RestController
@Slf4j
public class ServingInstanceQryFacadeImpl implements ServingInstanceQryFacade {
    @Resource
    private IServingInstanceService servingInstanceService;
    @Resource
    private OlapMapper olapMapper;
    @Resource
    private ObjectMapper objectMapper;

    @Override
    public Result<PageView<ServingInstanceDTO>> pageBy(ServingInstancePageByQry servingInstancePageByQry) {
        PageView<ServingInstance> solutionPageView = servingInstanceService.page(servingInstancePageByQry);
        PageView<ServingInstanceDTO> solutionDTOPageView = PageTool.pageView(solutionPageView,
                ServingInstanceDTO.class);
        return Result.success(solutionDTOPageView);
    }

    @Override
    public Result<ServingInstanceDTO> getById(ServingInstanceGetByIdQry servingInstanceGetByIdQry) {
        ServingInstance servingInstance = servingInstanceService.getById(servingInstanceGetByIdQry.getId());
        return Result.success(ConvertTool.convert(servingInstance, ServingInstanceDTO.class));
    }

    @Override
    @SneakyThrows
    public Result<PredictResultDTO> getPredictResult(GetPredictResultQry getPredictResultQry) {
        ServingInstance servingInstance = servingInstanceService.getById(getPredictResultQry.getInstanceId());
//        String tableName = OlapHelper.getScoreTableName(servingInstance.getSolutionId());
        String tableName = OlapHelper.getScoreTableName(251L);
        Map<String, Object> map;
        switch (getPredictResultQry.getScoreRangeType()) {
            case ALL:
                map = olapMapper.all(tableName);
                break;
            case TOP_N:
                Long n = Long.parseLong(getPredictResultQry.getValues().get(0).toString());
                map = olapMapper.topN(tableName, n);
                break;
            case TOP_PERCENT:
                Double percent = Double.parseDouble(getPredictResultQry.getValues().get(0).toString());
                map = olapMapper.topPercent(tableName, percent);
                break;
            case SCORE_RANGE:
                Double minScore = Double.parseDouble(getPredictResultQry.getValues().get(0).toString());
                Double maxScore = Double.parseDouble(getPredictResultQry.getValues().get(1).toString());
                map = olapMapper.targetScore(tableName, minScore, maxScore);
                break;
            default:
                throw new RuntimeException("不支持的类型");

        }
        PredictResultDTO predictResultDTO = new PredictResultDTO();
        predictResultDTO.setRatio(Double.parseDouble(map.get("ratio").toString()));
        predictResultDTO.setTotal(Long.parseLong(map.get("total").toString()));
        PredictResultDTO.ScoreRange scoreRange = new PredictResultDTO.ScoreRange();
        scoreRange.setMaxScore(Double.parseDouble(map.get("max_score").toString()));
        scoreRange.setMinScore(Double.parseDouble(map.get("min_score").toString()));
        feedInterval(predictResultDTO, getPredictResultQry.getScoreRangeType(), tableName,
                getPredictResultQry.getValues());
        predictResultDTO.setScoreRange(scoreRange);
        return Result.success(predictResultDTO);
    }

    private void feedInterval(PredictResultDTO predictResultDTO, ScoreRangeType scoreRangeType, String tableName,
                              List<Object> values) {
        List<Map<String, Object>> baseRange = olapMapper.getBaseRange(tableName);
        predictResultDTO.setBaseIntervals(baseRange.stream().map(item -> {
            PredictResultDTO.Interval interval = new PredictResultDTO.Interval();
            interval.setCname(item.get("score_range").toString());
            interval.setTotal(Long.parseLong(item.get("count").toString()));
            return interval;
        }).collect(Collectors.toList()));
        switch (scoreRangeType) {
            case ALL:
            case TOP_N:
            case TOP_PERCENT:
                Long remain = predictResultDTO.getTotal();
                List<PredictResultDTO.Interval> targetIntervals = Lists.newArrayList();
                for (PredictResultDTO.Interval interval : predictResultDTO.getBaseIntervals()) {
                    PredictResultDTO.Interval targetInterval = new PredictResultDTO.Interval();
                    if (remain > interval.getTotal()) {
                        targetInterval.setCname(interval.getCname());
                        targetInterval.setTotal(interval.getTotal());
                        targetIntervals.add(targetInterval);
                        remain -= interval.getTotal();
                    } else {
                        targetInterval.setCname(interval.getCname());
                        targetInterval.setTotal(remain);
                        targetIntervals.add(targetInterval);
                    }
                }
                predictResultDTO.setTargetIntervals(targetIntervals);
                break;
            case SCORE_RANGE:
                List<Map<String, Object>> rangeDistribution = olapMapper.getTargetScoreDistribution(tableName,
                                Double.parseDouble(values.get(0).toString()),
                                Double.parseDouble(values.get(1).toString()))
                        .stream().map(item -> {
                            Map<String, Object> m = Maps.newHashMap();
                            m.put(item.get("score_range").toString(), item.get("count"));
                            return m;
                        }).collect(Collectors.toList());
                Map<String, Object> reduce = rangeDistribution.stream().reduce(Maps.newHashMap(), (a, b) -> {
                    a.putAll(b);
                    return a;
                });
                predictResultDTO.setTargetIntervals(baseRange.stream().map(item -> {
                    PredictResultDTO.Interval interval = new PredictResultDTO.Interval();
                    String cname = item.get("score_range").toString();
                    interval.setCname(cname);
                    interval.setTotal(Long.parseLong(reduce.getOrDefault(cname, 0L).toString()));
                    return interval;
                }).collect(Collectors.toList()));
                break;
            default:
                throw new RuntimeException("不支持的类型");
        }
    }
}
