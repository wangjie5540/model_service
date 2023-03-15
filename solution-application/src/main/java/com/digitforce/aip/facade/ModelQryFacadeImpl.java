package com.digitforce.aip.facade;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.digitforce.aip.dto.data.ModelDTO;
import com.digitforce.aip.dto.data.ModelPackageDTO;
import com.digitforce.aip.dto.qry.ModelPackagePageByQry;
import com.digitforce.aip.dto.qry.ModelPageByQry;
import com.digitforce.aip.entity.Model;
import com.digitforce.aip.entity.ModelPackage;
import com.digitforce.aip.service.IModelPackageService;
import com.digitforce.aip.service.IModelService;
import com.digitforce.framework.api.dto.PageView;
import com.digitforce.framework.api.dto.Result;
import com.digitforce.framework.tool.ConvertTool;
import com.digitforce.framework.tool.PageTool;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class ModelQryFacadeImpl implements ModelQryFacade {
    @Resource
    private IModelPackageService modelPackageService;
    @Resource
    private IModelService modelService;

    @Override
    public Result<PageView<ModelPackageDTO>> modelPackagePageBy(ModelPackagePageByQry modelPackagePageByQry) {
        PageView<ModelPackage> modelPackagePageView = modelPackageService.page(modelPackagePageByQry);
        if (modelPackagePageView == null || modelPackagePageView.getList() == null) {
            return Result.success(null);
        }
        List<Long> packageIds =
                modelPackagePageView.getList().stream().map(ModelPackage::getId).collect(Collectors.toList());
        QueryWrapper<Model> wrapper = new QueryWrapper<>();
        wrapper.in("package_id", packageIds);
        List<Model> models = modelService.list(wrapper);
        Map<Long, List<Model>> modelMap = models.stream().collect(Collectors.groupingBy(Model::getPackageId));
        PageView<ModelPackageDTO> modelPackageDTOPageView = PageTool.pageView(modelPackagePageView,
                ModelPackageDTO.class);
        modelPackageDTOPageView.getList().forEach(modelPackageDTO -> {
            List<Model> modelList = modelMap.get(modelPackageDTO.getId());
            modelPackageDTO.setFittedModelList(ConvertTool.convert(modelList, ModelDTO.class));
        });
        return Result.success(modelPackageDTOPageView);
    }

    @Override
    public Result<PageView<ModelDTO>> modelPageBy(ModelPageByQry modelPageByQry) {
        PageView<Model> solutionPageView = modelService.page(modelPageByQry);
        PageView<ModelDTO> solutionDTOPageView = PageTool.pageView(solutionPageView,
                ModelDTO.class);
        return Result.success(solutionDTOPageView);
    }
}
