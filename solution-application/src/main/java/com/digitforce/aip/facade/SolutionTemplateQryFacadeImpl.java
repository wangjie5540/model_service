package com.digitforce.aip.facade;

import com.digitforce.aip.KubeflowHelper;
import com.digitforce.aip.config.KubeflowProperties;
import com.digitforce.aip.domain.SolutionTemplate;
import com.digitforce.aip.dto.data.PipelineDataSource;
import com.digitforce.aip.dto.data.PropertyDesc;
import com.digitforce.aip.dto.data.SolutionTemplateDTO;
import com.digitforce.aip.dto.data.TemplateStatusListDTO;
import com.digitforce.aip.dto.qry.SolutionTemplateGetByIdQry;
import com.digitforce.aip.dto.qry.SolutionTemplatePageByQry;
import com.digitforce.aip.enums.DataTypeEnum;
import com.digitforce.aip.enums.TemplateStatusEnum;
import com.digitforce.aip.mapper.SolutionTemplateMapper;
import com.digitforce.aip.model.Pipeline;
import com.digitforce.aip.service.qry.SolutionTemplateQryService;
import com.digitforce.framework.api.dto.PageView;
import com.digitforce.framework.api.dto.Result;
import com.digitforce.framework.tool.ConvertTool;
import com.digitforce.framework.tool.PageTool;
import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 方案查询接口实现类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 17:08
 */
@RestController
public class SolutionTemplateQryFacadeImpl implements SolutionTemplateQryFacade {
    @Resource
    private SolutionTemplateQryService solutionTemplateQryService;
    @Resource
    private KubeflowProperties kubeflowProperties;
    @Resource
    private SolutionTemplateMapper solutionTemplateMapper;

    @Override
    public Result<SolutionTemplateDTO> getById(SolutionTemplateGetByIdQry solutionGetByIdQry) {
        SolutionTemplate solutionTemplate = solutionTemplateQryService.getById(solutionGetByIdQry.getId());
        if (solutionTemplate == null) {
            return Result.success(null);
        }
        SolutionTemplateDTO solutionTemplateDTO = ConvertTool.convert(solutionTemplate, SolutionTemplateDTO.class);
        Pipeline pipeline = KubeflowHelper.getPipelineDetail(kubeflowProperties.getHost(),
                kubeflowProperties.getPort(), solutionTemplateDTO.getPipelineId());
//        solutionTemplateDTO.setPipelineParameter(GsonUtil.gsonToBean(pipeline.getDescription(),
//                PipelineParameterDTO.class));
        solutionTemplateMapper.browseCountInc(solutionGetByIdQry.getId());
        PipelineDataSource dataSource = getDatasource();
        solutionTemplateDTO.setDataSource(dataSource);
        // TODO: mock数据
        solutionTemplate.setUpdateUser("admin");
        return Result.success(solutionTemplateDTO);
    }

    private PipelineDataSource getDatasource() {
        // TODO mvp版本mock数据
        PipelineDataSource dataSource = new PipelineDataSource();
        List<PropertyDesc> goodsProperties = Lists.newArrayList();
        goodsProperties.add(new PropertyDesc("category", "品类", DataTypeEnum.STRING));
        goodsProperties.add(new PropertyDesc("tags", "标签", DataTypeEnum.STRING));
        goodsProperties.add(new PropertyDesc("author", "作者", DataTypeEnum.STRING));
        goodsProperties.add(new PropertyDesc("merchant", "商家", DataTypeEnum.STRING));
        goodsProperties.add(new PropertyDesc("publish_time", "发布时间", DataTypeEnum.DATETIME));
        dataSource.setGoodsData(goodsProperties);
        List<PropertyDesc> userProperties = Lists.newArrayList();
        userProperties.add(new PropertyDesc("userId", "用户id", DataTypeEnum.STRING));
        userProperties.add(new PropertyDesc("age", "用户年龄", DataTypeEnum.NUMERIC));
        userProperties.add(new PropertyDesc("city", "城市", DataTypeEnum.STRING));
        userProperties.add(new PropertyDesc("channel_user_id", "用户渠道id", DataTypeEnum.STRING));
        userProperties.add(new PropertyDesc("channel_type", "渠道类型", DataTypeEnum.STRING));
        userProperties.add(new PropertyDesc("sex", "性别", DataTypeEnum.STRING));
        userProperties.add(new PropertyDesc("birthday", "生日", DataTypeEnum.STRING));
        userProperties.add(new PropertyDesc("education", "学历", DataTypeEnum.STRING));
        userProperties.add(new PropertyDesc("constellation", "星座", DataTypeEnum.STRING));
        userProperties.add(new PropertyDesc("country_code", "国籍编码", DataTypeEnum.STRING));
        userProperties.add(new PropertyDesc("country_code", "国籍编码", DataTypeEnum.STRING));
        userProperties.add(new PropertyDesc("city_code", "故乡城市", DataTypeEnum.STRING));
        userProperties.add(new PropertyDesc("signup_datetime", "注册时间", DataTypeEnum.DATETIME));
        userProperties.add(new PropertyDesc("last_view_time", "末次访问时间", DataTypeEnum.DATETIME));
        dataSource.setUserData(userProperties);
        List<PropertyDesc> orderProperties = Lists.newArrayList();
        orderProperties.add(new PropertyDesc("amount", "订单金额", DataTypeEnum.NUMERIC));
        orderProperties.add(new PropertyDesc("total_amount", "交易总金额", DataTypeEnum.NUMERIC));
        orderProperties.add(new PropertyDesc("count", "商品数量", DataTypeEnum.NUMERIC));
        dataSource.setOrderData(orderProperties);
        // TODO 流量的数据结构需要设计
        return dataSource;
    }

    @Override
    public Result<PageView<SolutionTemplateDTO>> pageBy(@RequestBody SolutionTemplatePageByQry templatePageByQry) {
        PageView<SolutionTemplate> templatePageView = solutionTemplateQryService.pageBy(templatePageByQry);
        PageView<SolutionTemplateDTO> solutionDTOPageView = PageTool.pageView(templatePageView,
                SolutionTemplateDTO.class);
        solutionDTOPageView.getList().forEach(s -> s.setDataSource(getDatasource()));
        return Result.success(solutionDTOPageView);
    }

    @Override
    public Result<List<SolutionTemplateDTO>> listBy() {
        SolutionTemplate solutionTemplate = new SolutionTemplate();
        solutionTemplate.setStatus(TemplateStatusEnum.ONLINE);
        List<SolutionTemplate> solutionTemplateList = solutionTemplateQryService.listBy(solutionTemplate);
        List<SolutionTemplateDTO> solutionTemplateDTOS =
                ConvertTool.convert(solutionTemplateList, SolutionTemplateDTO.class);
        solutionTemplateDTOS.forEach(s -> s.setDataSource(getDatasource()));
        return Result.success(solutionTemplateDTOS);
    }

    @Override
    public Result<TemplateStatusListDTO> statusListBy() {
        TemplateStatusListDTO templateStatusListDTO = new TemplateStatusListDTO();
        templateStatusListDTO.setStatusList(Arrays.asList(TemplateStatusEnum.values()));
        return Result.success(templateStatusListDTO);
    }
}
