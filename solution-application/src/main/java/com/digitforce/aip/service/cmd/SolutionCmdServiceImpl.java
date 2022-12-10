package com.digitforce.aip.service.cmd;

import com.digitforce.aip.config.KubeflowProperties;
import com.digitforce.aip.domain.Solution;
import com.digitforce.aip.dto.cmd.SolutionAddCmd;
import com.digitforce.aip.dto.cmd.SolutionModifyCmd;
import com.digitforce.aip.enums.SolutionStatusEnum;
import com.digitforce.aip.mapper.SolutionMapper;
import com.digitforce.aip.mapper.SolutionTemplateMapper;
import com.digitforce.aip.repository.SolutionRepository;
import com.digitforce.framework.operation.DefaultService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 方案命令类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/05 13:59
 */
@Service
@Slf4j
public class SolutionCmdServiceImpl extends DefaultService<Solution> implements SolutionCmdService {
    @Resource
    private SolutionRepository solutionRepository;
    @Resource
    private KubeflowProperties kubeflowProperties;
    @Resource
    private SolutionMapper solutionMapper;
    @Resource
    private SolutionTemplateMapper solutionTemplateMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void add(SolutionAddCmd solutionAddCmd) {
        // TODO
    }

    @Override
    public void on(Long id) {
        Solution solution = solutionRepository.getById(id);
        if (solution.getStatus() == SolutionStatusEnum.FINISHED) {
            solution = new Solution();
            solution.setId(id);
            solution.setStatus(SolutionStatusEnum.ONLINE);
            solutionRepository.modifyById(solution);
        }
    }

    @Override
    public void off(Long id) {
        Solution solution = solutionRepository.getById(id);
        if (solution.getStatus() == SolutionStatusEnum.ONLINE) {
            solution = new Solution();
            solution.setId(id);
            solution.setStatus(SolutionStatusEnum.FINISHED);
            solutionRepository.modifyById(solution);
        }
    }

    @Override
    public void execute(Long id) {
        // TODO
    }

    @Override
    public void batchExecute(List<Long> ids) {
        ids.forEach(this::execute);
    }

    @Override
    public void onExecuting(Long taskId, Long taskInstanceId) {
    }

    @Override
    public void onFinished(Long taskId, Long taskInstanceId) {

    }

    @Override
    public void stop(Long id) {
    }

    @Override
    public void onStopping(Long taskId, Long taskInstanceId) {
    }

    @Override
    public void onFailed(Long taskId, Long taskInstanceId) {
    }

    @Override
    public void delete(Long id) {
        Solution solution = solutionRepository.getById(id);
        if (solution.getStatus() == SolutionStatusEnum.ONLINE || solution.getStatus() == SolutionStatusEnum.EXECUTING) {
            throw new RuntimeException("solution is using");
        }
        solutionRepository.removeById(id);
    }

    @Override
    public void batchDelete(List<Long> ids) {
        ids.forEach(this::delete);
    }

    @Override
    public void modifyById(SolutionModifyCmd solutionModifyCmd) {
    }

    @Override
    public SolutionRepository getRepository() {
        return solutionRepository;
    }
}
