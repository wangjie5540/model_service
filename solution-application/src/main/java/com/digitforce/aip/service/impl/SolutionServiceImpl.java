package com.digitforce.aip.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.digitforce.aip.dto.cmd.SolutionAddCmd;
import com.digitforce.aip.dto.cmd.SolutionPublishCmd;
import com.digitforce.aip.dto.cmd.SolutionUnPublishCmd;
import com.digitforce.aip.dto.qry.SolutionPageByQry;
import com.digitforce.aip.entity.Scene;
import com.digitforce.aip.entity.SceneVersion;
import com.digitforce.aip.entity.Solution;
import com.digitforce.aip.enums.SolutionRunTypeEnum;
import com.digitforce.aip.enums.SolutionStatusEnum;
import com.digitforce.aip.enums.StageEnum;
import com.digitforce.aip.mapper.SceneMapper;
import com.digitforce.aip.mapper.SolutionMapper;
import com.digitforce.aip.quartz.SolutionQuartzJob;
import com.digitforce.aip.service.AutoMLService;
import com.digitforce.aip.service.ISceneService;
import com.digitforce.aip.service.ISceneVersionService;
import com.digitforce.aip.service.ISolutionRunService;
import com.digitforce.aip.service.ISolutionService;
import com.digitforce.aip.service.component.TemplateComponent;
import com.digitforce.aip.utils.PageUtil;
import com.digitforce.framework.api.dto.PageView;
import com.digitforce.framework.context.TenantContext;
import com.digitforce.framework.tool.ConvertTool;
import com.digitforce.framework.tool.PageTool;
import lombok.SneakyThrows;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.TriggerBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 方案表 服务实现类
 * </p>
 *
 * @author wangtonggui
 * @since 2022-12-10
 */
@Service
public class SolutionServiceImpl extends ServiceImpl<SolutionMapper, Solution> implements ISolutionService {
    @Resource
    private ISolutionRunService solutionRunService;
    @Resource
    private Scheduler scheduler;
    @Resource
    private SceneMapper sceneMapper;
    @Resource
    private ISceneService sceneService;
    @Resource
    private ISceneVersionService sceneVersionService;
    @Resource
    private TemplateComponent templateComponent;
    @Resource
    private AutoMLService autoMLService;

    @Override
    public void createAndRun(SolutionAddCmd solutionAddCmd) {
        Solution solution = ConvertTool.convert(solutionAddCmd, Solution.class);
        Scene scene = sceneService.getById(solutionAddCmd.getSceneId());
        SceneVersion sceneVersion = sceneVersionService.getById(scene.getVidInUse());
        String pipelineTemplate =
                templateComponent.getPipelineTemplate(sceneVersion.getPipelineName(), StageEnum.TRAIN);
        solution.setSceneType(scene.getSceneType());
        solution.setPipelineTemplate(pipelineTemplate);
        solution.setTemplateParams(solutionAddCmd.getTemplateParams());
        solution.setCreateUser(TenantContext.tenant().getUserAccount());
        solution.setUpdateUser(TenantContext.tenant().getUserAccount());
        super.save(solution);
        // 增加统计数量
        sceneMapper.increaseSolutionCount(solutionAddCmd.getSceneId());
        if (solutionAddCmd.isAutoML()) {
            // TODO 构造参数
            String autoMlRunId = autoMLService.createTask(null);
        } else {
            solutionRunService.createRun(solution, SolutionRunTypeEnum.DEBUG, solutionAddCmd.getTemplateParams());
        }
    }

    @Override
    public void publish(SolutionPublishCmd solutionPublishCmd) {
        Solution solution = ConvertTool.convert(solutionPublishCmd, Solution.class);
        Solution savedSolution = getById(solution.getId());
        if (Objects.isNull(savedSolution)) {
            throw new RuntimeException("方案不存在");
        }
        switch (savedSolution.getStatus()) {
            case PUBLISHED:
                throw new RuntimeException("方案已发布");
            case EXECUTING:
                throw new RuntimeException("方案正在执行");
            case ERROR:
                throw new RuntimeException("方案执行失败");
            case READY:
                solution.setStatus(SolutionStatusEnum.PUBLISHED);
                updateById(solution);
                scheduleJob(solutionPublishCmd);
                break;
            default:
                throw new RuntimeException("方案状态异常");
        }
    }

    @SneakyThrows
    private void scheduleJob(SolutionPublishCmd solutionPublishCmd) {
        Integer tenantId = TenantContext.tenant().getTenantId();
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("solutionId", solutionPublishCmd.getId());
        JobDetail jobDetail = JobBuilder.newJob(SolutionQuartzJob.class)
                .withIdentity(solutionPublishCmd.getId().toString(), tenantId.toString())
                .setJobData(jobDataMap)
                .build();
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(solutionPublishCmd.getCron());
        CronTrigger cronTrigger =
                TriggerBuilder.newTrigger().withSchedule(cronScheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }

    @SneakyThrows
    @Override
    public void unPublish(SolutionUnPublishCmd solutionUnPublishCmd) {
        Solution solution = getById(solutionUnPublishCmd.getId());
        if (Objects.isNull(solution)) {
            throw new RuntimeException("方案不存在");
        }
        if (solution.getStatus() != SolutionStatusEnum.PUBLISHED) {
            throw new RuntimeException("方案未发布");
        }
        solution = ConvertTool.convert(solutionUnPublishCmd, Solution.class);
        solution.setStatus(SolutionStatusEnum.READY);
        updateById(solution);
        scheduler.deleteJob(JobKey.jobKey(solutionUnPublishCmd.getId().toString(),
                TenantContext.tenant().getTenantId().toString()));
    }

    @Override
    public PageView<Solution> page(SolutionPageByQry solutionPageByQry) {
        QueryWrapper<Solution> queryWrapper =
                new QueryWrapper<>(BeanUtil.toBean(solutionPageByQry.getClause(), Solution.class));
        Map<String, Object> map = BeanUtil.beanToMap(solutionPageByQry.getLikeClause(), true, true);
        if (!Objects.isNull(map)) {
            map.forEach(queryWrapper::like);
        }
        Page<Solution> page = PageUtil.page(solutionPageByQry);
        page = super.page(page, queryWrapper);
        return PageTool.pageView(page);
    }
}
