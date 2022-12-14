package com.digitforce.aip.service.component;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.digitforce.aip.entity.SolutionRun;
import com.digitforce.aip.enums.RunStatusEnum;
import com.digitforce.aip.service.ISolutionRunService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SchedulerTask {
    @Resource
    private ISolutionRunService solutionRunService;

    /**
     * 每5秒检测一次状态
     * TODO 后续将切换为quartz
     */
    @Scheduled(fixedRate = 5000)
    public void checkStatus() {
        Page<SolutionRun> page = Page.of(1, 20);
        page = solutionRunService.page(page, new LambdaQueryWrapper<SolutionRun>().eq(SolutionRun::getStatus,
                RunStatusEnum.Running));
        page.getRecords().forEach(record -> {
            RunStatusEnum status = solutionRunService.getRunStatus(record.getPRunId());
            if (status != RunStatusEnum.Running) {
                record.setStatus(status);
                solutionRunService.updateById(record);
            }
        });
    }
}
