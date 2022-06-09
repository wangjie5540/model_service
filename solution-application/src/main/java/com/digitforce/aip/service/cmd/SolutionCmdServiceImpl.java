package com.digitforce.aip.service.cmd;

import com.digitforce.aip.domain.Solution;
import com.digitforce.aip.dto.cmd.SolutionAddCmd;
import com.digitforce.aip.dto.cmd.SolutionModifyCmd;
import com.digitforce.aip.repository.SolutionRepository;
import com.digitforce.aip.validator.SolutionValidator;
import com.digitforce.framework.operation.DefaultService;
import com.digitforce.framework.tool.ConvertTool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 方案命令服务实现类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/05 13:59
 */
@Service
public class SolutionCmdServiceImpl extends DefaultService<Solution> implements SolutionCmdService {
    @Resource
    private SolutionValidator solutionValidator;
    @Resource
    private SolutionRepository solutionRepository;

    @Override
    public void add(SolutionAddCmd solutionAddCmd) {
        // TODO
//        solutionValidator.validate(null);
        Solution solution = ConvertTool.convert(solutionAddCmd, Solution.class);
        solutionRepository.save(solution);
    }

    @Override
    public void on(Long id) {
        Solution solution = new Solution();
        solution.setId(id);
        solution.setStatus(true);
        solutionRepository.modifyById(solution);
    }

    @Override
    public void off(Long id) {
        Solution solution = new Solution();
        solution.setId(id);
        solution.setStatus(false);
        solutionRepository.modifyById(solution);
    }

    @Override
    public void delete(Long id) {
        Solution solution = new Solution();
        solution.setId(id);
        solution.setDeleted(false);
        solutionRepository.modifyById(solution);
    }

    @Override
    public void modify(SolutionModifyCmd solutionModifyCmd) {
        Solution solution = ConvertTool.convert(solutionModifyCmd, Solution.class);
        solutionRepository.modifyById(solution);
    }

    @Override
    public SolutionRepository getRepository() {
        return solutionRepository;
    }
}
