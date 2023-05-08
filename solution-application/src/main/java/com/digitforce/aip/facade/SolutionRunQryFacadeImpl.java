package com.digitforce.aip.facade;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.digitforce.aip.dto.data.ModelMetric;
import com.digitforce.aip.dto.data.Roc;
import com.digitforce.aip.dto.data.SolutionRunDTO;
import com.digitforce.aip.dto.qry.SolutionRunLatestVersionQry;
import com.digitforce.aip.dto.qry.SolutionRunPageByQry;
import com.digitforce.aip.entity.Model;
import com.digitforce.aip.entity.SolutionRun;
import com.digitforce.aip.service.IModelService;
import com.digitforce.aip.service.ISolutionRunService;
import com.digitforce.framework.api.dto.PageView;
import com.digitforce.framework.api.dto.Result;
import com.digitforce.framework.tool.ConvertTool;
import com.digitforce.framework.tool.PageTool;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class SolutionRunQryFacadeImpl implements SolutionRunQryFacade {
    @Resource
    private ISolutionRunService solutionRunService;
    @Resource
    private IModelService modelService;
    @Resource
    private ObjectMapper objectMapper;

    @Override
    public Result<PageView<SolutionRunDTO>> pageBy(SolutionRunPageByQry solutionRunPageByQry) {
        PageView<SolutionRun> solutionPageView = solutionRunService.page(solutionRunPageByQry);
        List<Long> packageIds =
                solutionPageView.getList().stream().map(SolutionRun::getPackageId).collect(Collectors.toList());
        List<Model> models;
        if (!CollectionUtils.isEmpty(packageIds)) {
            QueryWrapper<Model> wrapper = new QueryWrapper<>();
            wrapper.in("package_id", packageIds);
            models = modelService.list(wrapper);
        } else {
            models = Lists.newArrayList();
        }
        Map<Long, List<Model>> modelMap = models.stream().collect(Collectors.groupingBy(Model::getPackageId));
        PageView<SolutionRunDTO> solutionDTOPageView = PageTool.pageView(solutionPageView,
                SolutionRunDTO.class);
        solutionDTOPageView.getList().forEach(solutionRunDTO -> {
            // 计算训练时长
            Duration duration = Duration.between(solutionRunDTO.getCreateTime(), solutionRunDTO.getUpdateTime());
            solutionRunDTO.setTrainTime(duration.getSeconds());

            List<Model> modelList = modelMap.get(solutionRunDTO.getPackageId());
            feedMetrics(modelList, solutionRunDTO);
        });
        return Result.success(solutionDTOPageView);
    }

    @Override
    public Result<SolutionRunDTO> getLatestVersion(SolutionRunLatestVersionQry solutionRunLatestVersionQry) {
        SolutionRun solutionRun = solutionRunService.getLatestVersion(solutionRunLatestVersionQry.getSolutionId());
        if (solutionRun == null) {
            return Result.success(null);
        }
        Long packageId = solutionRun.getPackageId();
        QueryWrapper<Model> wrapper = new QueryWrapper<>();
        wrapper.eq("package_id", packageId);
        List<Model> models = modelService.list(wrapper);
        SolutionRunDTO solutionRunDTO = ConvertTool.convert(solutionRun, SolutionRunDTO.class);
        // 计算训练时长
        Duration duration = Duration.between(solutionRunDTO.getCreateTime(), solutionRunDTO.getUpdateTime());
        solutionRunDTO.setTrainTime(duration.getSeconds());
        feedMetrics(models, solutionRunDTO);
        return Result.success(solutionRunDTO);
    }


    private void feedMetrics(List<Model> modelList, SolutionRunDTO solutionRunDTO) {
        // TODO 由于产品上还未设计到一个pipeline有多个模型，这里仅去第一个模型
        if (modelList != null && modelList.size() > 0) {
            Model model = modelList.get(0);
            List<ModelMetric> metricsList = model.getMetricsList();
            try {
                // mybatis-plus获取的list结果序列化后会变成map，这里需要重新反序列化
                metricsList = objectMapper.readValue(objectMapper.writeValueAsString(metricsList),
                        new TypeReference<List<ModelMetric>>() {
                        });
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            metricsList.forEach(modelMetric -> {
                switch (modelMetric.getName()) {
                    case "precision":
                        solutionRunDTO.setPrecision(Float.parseFloat(modelMetric.getValue().toString()));
                        break;
                    case "recall":
                        solutionRunDTO.setRecall(Float.parseFloat(modelMetric.getValue().toString()));
                        break;
                    case "f1":
                        solutionRunDTO.setF1(Float.parseFloat(modelMetric.getValue().toString()));
                        break;
                    case "auc":
                        solutionRunDTO.setAuc(Float.parseFloat(modelMetric.getValue().toString()));
                        break;
                    case "roc":
                        try {
                            String rocStr = objectMapper.writeValueAsString(modelMetric.getValue());
                            solutionRunDTO.setRoc(objectMapper.readValue(rocStr, Roc.class));
                        } catch (JsonProcessingException e) {
                            log.error("roc序列化失败", e);
                        }
                        break;
                    default:
                        log.error("未知的metricName:{}", modelMetric.getName());
                }
            });
        }
    }
}
