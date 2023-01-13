package com.digitforce.aip.facade;

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
import com.digitforce.framework.tool.PageTool;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ModelQryFacadeImpl implements ModelQryFacade {
    @Resource
    private IModelPackageService modelPackageService;
    @Resource
    private IModelService modelService;

    @Override
    public Result<PageView<ModelPackageDTO>> modelPackagePageBy(ModelPackagePageByQry modelPackagePageByQry) {
        PageView<ModelPackage> solutionPageView = modelPackageService.page(modelPackagePageByQry);
        PageView<ModelPackageDTO> solutionDTOPageView = PageTool.pageView(solutionPageView,
                ModelPackageDTO.class);
        return Result.success(solutionDTOPageView);
    }

    @Override
    public Result<PageView<ModelDTO>> modelPageBy(ModelPageByQry modelPageByQry) {
        PageView<Model> solutionPageView = modelService.page(modelPageByQry);
        PageView<ModelDTO> solutionDTOPageView = PageTool.pageView(solutionPageView,
                ModelDTO.class);
        return Result.success(solutionDTOPageView);
    }
}
