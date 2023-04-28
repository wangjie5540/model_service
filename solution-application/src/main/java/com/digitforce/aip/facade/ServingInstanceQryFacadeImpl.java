package com.digitforce.aip.facade;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.digitforce.aip.consts.CommonConst;
import com.digitforce.aip.dto.data.PredictDetailDTO;
import com.digitforce.aip.dto.data.PredictResultDTO;
import com.digitforce.aip.dto.data.ServingInstanceDTO;
import com.digitforce.aip.dto.data.SolutionServingDTO;
import com.digitforce.aip.dto.qry.GetAleQry;
import com.digitforce.aip.dto.qry.GetPredictDetailByIdQry;
import com.digitforce.aip.dto.qry.GetPredictResultQry;
import com.digitforce.aip.dto.qry.GetShapleyQry;
import com.digitforce.aip.dto.qry.PredictDetailPageByQry;
import com.digitforce.aip.dto.qry.ServingInstanceGetByIdQry;
import com.digitforce.aip.dto.qry.ServingInstancePageByQry;
import com.digitforce.aip.entity.PredictDetail;
import com.digitforce.aip.entity.ServingInstance;
import com.digitforce.aip.entity.SolutionServing;
import com.digitforce.aip.enums.ScoreRangeType;
import com.digitforce.aip.mapper.PredictResultMapper;
import com.digitforce.aip.service.IServingInstanceService;
import com.digitforce.aip.service.ISolutionServingService;
import com.digitforce.aip.service.component.DownloadService;
import com.digitforce.aip.utils.OlapHelper;
import com.digitforce.framework.api.dto.PageView;
import com.digitforce.framework.api.dto.Result;
import com.digitforce.framework.tool.ConvertTool;
import com.digitforce.framework.tool.PageTool;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.swagger.v3.oas.annotations.Operation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

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
    private PredictResultMapper predictResultMapper;
    @Resource
    private DownloadService downloadService;
    @Resource
    private ISolutionServingService solutionServingService;

    public static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    }

    @Override
    public Result<PageView<ServingInstanceDTO>> pageBy(ServingInstancePageByQry servingInstancePageByQry) {
        PageView<ServingInstance> instancePageView = servingInstanceService.page(servingInstancePageByQry);
        List<Long> servingIds =
                instancePageView.getList().stream().map(ServingInstance::getServingId).collect(Collectors.toList());
        Map<Long, SolutionServing> solutionServingMap;
        if (CollectionUtils.isEmpty(servingIds)) {
            solutionServingMap = Maps.newHashMap();
        } else {
            QueryWrapper<SolutionServing> wrapper = new QueryWrapper<>();
            wrapper.in("id", servingIds);
            List<SolutionServing> solutionServings = solutionServingService.list(wrapper);
            solutionServingMap =
                    solutionServings.stream().collect(Collectors.toMap(SolutionServing::getId, Function.identity()));
        }
        PageView<ServingInstanceDTO> instanceDTOPageView = PageTool.pageView(instancePageView,
                ServingInstanceDTO.class);
        instanceDTOPageView.getList().forEach(instanceDTO -> {
            SolutionServing solutionServing = solutionServingMap.get(instanceDTO.getServingId());
            if (solutionServing != null) {
                instanceDTO.setSolutionServingDTO(ConvertTool.convert(solutionServing, SolutionServingDTO.class));
            }
        });
        return Result.success(instanceDTOPageView);
    }

    @Override
    public Result<ServingInstanceDTO> getById(ServingInstanceGetByIdQry servingInstanceGetByIdQry) {
        ServingInstance servingInstance = servingInstanceService.getById(servingInstanceGetByIdQry.getId());
        return Result.success(ConvertTool.convert(servingInstance, ServingInstanceDTO.class));
    }

    @Override
    @SneakyThrows
    public Result<PredictResultDTO> getPredictStatistics(GetPredictResultQry getPredictResultQry) {
        try {

            ServingInstance servingInstance = servingInstanceService.getById(getPredictResultQry.getInstanceId());
            String tableName = OlapHelper.getScoreTableName(servingInstance.getSolutionId());
            Map<String, Object> map;
            switch (getPredictResultQry.getScoreRangeType()) {
                case ALL:
                    map = predictResultMapper.all(tableName, servingInstance.getId());
                    break;
                case TOP_N:
                    Long n = Long.parseLong(getPredictResultQry.getValues().get(0).toString());
                    map = predictResultMapper.topN(tableName, servingInstance.getId(), n);
                    break;
                case TOP_PERCENT:
                    Double percent = Double.parseDouble(getPredictResultQry.getValues().get(0).toString());
                    map = predictResultMapper.topPercent(tableName, servingInstance.getId(), percent);
                    break;
                case SCORE_RANGE:
                    Double minScore = Double.parseDouble(getPredictResultQry.getValues().get(0).toString());
                    Double maxScore = Double.parseDouble(getPredictResultQry.getValues().get(1).toString());
                    map = predictResultMapper.targetScore(tableName, servingInstance.getId(), minScore, maxScore);
                    break;
                default:
                    throw new RuntimeException("不支持的类型");

            }
            PredictResultDTO predictResultDTO = new PredictResultDTO();
            predictResultDTO.setRatio(Double.parseDouble(map.get("ratio").toString()));
            predictResultDTO.setTotal(Long.parseLong(map.get("total").toString()));
            PredictResultDTO.ScoreRange scoreRange = new PredictResultDTO.ScoreRange();
            scoreRange.setMaxScore(map.get("max_score") == null ? null :
                    Double.parseDouble(map.get("max_score").toString()));
            scoreRange.setMinScore(map.get("min_score") == null ? null :
                    Double.parseDouble(map.get("min_score").toString()));
            feedInterval(predictResultDTO, getPredictResultQry.getScoreRangeType(), tableName, servingInstance.getId(),
                    getPredictResultQry.getValues());
            predictResultDTO.setScoreRange(scoreRange);
            return Result.success(predictResultDTO);
        } catch (Exception e) {
            log.error("get getPredictStatistics error", e);
            return Result.success(null);
        }
    }

    @Override
    public Result<PageView<PredictDetailDTO>> pageByPredictDetail(PredictDetailPageByQry predictResultPageByQry) {
        // 给默认值
        predictResultPageByQry.setMinScore(predictResultPageByQry.getMinScore() == null ? 0 :
                predictResultPageByQry.getMinScore());
        predictResultPageByQry.setMaxScore(predictResultPageByQry.getMaxScore() == null ? 1 :
                predictResultPageByQry.getMaxScore());
        predictResultPageByQry.setPageNum(predictResultPageByQry.getPageNum() == null ? 1 :
                predictResultPageByQry.getPageNum());
        predictResultPageByQry.setPageSize(predictResultPageByQry.getPageSize() == null ? 10 :
                predictResultPageByQry.getPageSize());
        try {
            ServingInstance servingInstance = servingInstanceService.getById(predictResultPageByQry.getInstanceId());
            PageView<PredictDetailDTO> pageView = new PageView<>();
            if (predictResultPageByQry.getTotal() == null) {
                String scoreTableName = OlapHelper.getScoreTableName(servingInstance.getSolutionId());
                Long count = predictResultMapper.countPredictDetail(scoreTableName,
                        predictResultPageByQry.getInstanceId());
                pageView.setCount(count.intValue());
            } else {
                pageView.setCount(Integer.parseInt(predictResultPageByQry.getTotal().toString()));
            }
            String tableName = OlapHelper.getScoreTableName(servingInstance.getSolutionId());
            Integer startIndex = (predictResultPageByQry.getPageNum() - 1) * predictResultPageByQry.getPageSize();
            List<PredictDetail> predictDetailList;
            if (StrUtil.isEmptyIfStr(predictResultPageByQry.getUserId())) {
                predictDetailList = predictResultMapper.getPredictDetailList(tableName,
                        predictResultPageByQry.getInstanceId(),
                        // TODO 这里涉及到精度问题，需要优化
                        predictResultPageByQry.getMinScore() - 0.0000001,
                        predictResultPageByQry.getMaxScore() + 0.0000001,
                        predictResultPageByQry.getPageSize() < pageView.getCount() ?
                                predictResultPageByQry.getPageSize() :
                                pageView.getCount(),
                        startIndex
                );
            } else {
                predictDetailList = predictResultMapper.getPredictDetailListByUserId(tableName,
                        predictResultPageByQry.getInstanceId(),
                        // TODO 这里涉及到精度问题，需要优化
                        predictResultPageByQry.getMinScore() - 0.0000001,
                        predictResultPageByQry.getMaxScore() + 0.0000001,
                        predictResultPageByQry.getPageSize() < pageView.getCount() ?
                                predictResultPageByQry.getPageSize() :
                                pageView.getCount(),
                        startIndex,
                        predictResultPageByQry.getUserId()
                );
            }
            List<PredictDetailDTO> predictDetailDTOList = ConvertTool.convert(predictDetailList,
                    PredictDetailDTO.class);
            pageView.setList(predictDetailDTOList);
            return Result.success(pageView);
        } catch (Exception e) {
            log.error("pageByPredictDetail error", e);
            return Result.success(null);
        }
    }

    @SneakyThrows
    @GetMapping("/solution/servingInstance/streamTargetPredictDetail")
    @Operation(summary = "流式获取目标预测明细数据", tags = CommonConst.SWAGGER_TAG_SERVING_INSTANCE_QRY)
    public void streamTargetPredictDetail(
            @RequestParam Long instanceId,
            @RequestParam Double minScore,
            @RequestParam Double maxScore,
            @RequestParam Long total,
            HttpServletResponse response
    ) {
        response.setContentType("text/csv; charset=UTF-8");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=user_score.csv");

        ServingInstance servingInstance = servingInstanceService.getById(instanceId);

        downloadService.downloadResult(response.getOutputStream(), servingInstance.getSolutionId(),
                servingInstance.getId(), minScore, maxScore, total);
    }

    @Override
    public Result<PredictDetailDTO> getPredictDetailById(GetPredictDetailByIdQry getPredictDetailByIdQry) {
        ServingInstance servingInstance = servingInstanceService.getById(getPredictDetailByIdQry.getInstanceId());
        String tableName = OlapHelper.getScoreTableName(servingInstance.getSolutionId());
        PredictDetail predictDetail = predictResultMapper.getPredictDetailByUserId(tableName,
                getPredictDetailByIdQry.getInstanceId(), getPredictDetailByIdQry.getUserId());
        return Result.success(ConvertTool.convert(predictDetail, PredictDetailDTO.class));
    }

    @Override
    @SneakyThrows
    public Result<Object> getAle(GetAleQry getAleQry) {
        ServingInstance servingInstance = servingInstanceService.getById(getAleQry.getInstanceId());
        if (StrUtil.isEmptyIfStr(servingInstance.getAle())) {
            return Result.success(null);
        }
        return Result.success(objectMapper.readValue(servingInstance.getAle(), Object.class));
    }

    @Override
    @SneakyThrows
    public Result<Object> getShapley(GetShapleyQry getShapleyQry) {
        ServingInstance servingInstance = servingInstanceService.getById(getShapleyQry.getInstanceId());
        String tableName = OlapHelper.getShapleyTableName(servingInstance.getSolutionId());

        String shapely;
        try {
            shapely = predictResultMapper.getShapley(tableName, getShapleyQry.getInstanceId(),
                    getShapleyQry.getUserId());
        } catch (Exception e) {
            log.error("getShapley error", e);
            shapely = null;
        }
        if (StrUtil.isEmptyIfStr(shapely)) {
            return Result.success(null);
        }
        return Result.success(objectMapper.readValue(shapely, Object.class));
    }

    private void feedInterval(PredictResultDTO predictResultDTO, ScoreRangeType scoreRangeType, String tableName,
            Long instanceId, List<Object> values) {
        List<Map<String, Object>> baseRange = predictResultMapper.getBaseRange(tableName, instanceId);
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
                    targetInterval.setCname(interval.getCname());
                    if (remain > interval.getTotal()) {
                        targetInterval.setTotal(interval.getTotal());
                    } else {
                        targetInterval.setTotal(remain);
                    }
                    targetIntervals.add(targetInterval);
                    remain = remain - interval.getTotal() > 0 ? remain - interval.getTotal() : 0;
                }
                predictResultDTO.setTargetIntervals(targetIntervals);
                break;
            case SCORE_RANGE:
                List<Map<String, Object>> rangeDistribution = predictResultMapper.getTargetScoreDistribution(tableName,
                                instanceId,
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
