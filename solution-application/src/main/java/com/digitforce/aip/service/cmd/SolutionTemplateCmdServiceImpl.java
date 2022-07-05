package com.digitforce.aip.service.cmd;

import com.digitforce.aip.domain.SolutionTemplate;
import com.digitforce.aip.dto.cmd.SolutionTemplateAddCmd;
import com.digitforce.aip.dto.cmd.SolutionTemplateModifyCmd;
import com.digitforce.aip.repository.SolutionTemplateRepository;
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
public class SolutionTemplateCmdServiceImpl extends DefaultService<SolutionTemplate> implements SolutionTemplateCmdService {
    @Resource
    private SolutionValidator solutionValidator;
    @Resource
    private SolutionTemplateRepository solutionRepository;

    @Override
    public void add(SolutionTemplateAddCmd solutionAddCmd) {
//        solutionValidator.validate(null);
        SolutionTemplate solutionTemplate = ConvertTool.convert(solutionAddCmd, SolutionTemplate.class);
        solutionRepository.save(solutionTemplate);
    }

    @Override
    public void on(Long id) {
        SolutionTemplate solution = new SolutionTemplate();
        solution.setId(id);
        solution.setStatus(true);
        solutionRepository.modifyById(solution);
    }

    @Override
    public void off(Long id) {
        SolutionTemplate solution = new SolutionTemplate();
        solution.setId(id);
        solution.setStatus(false);
        solutionRepository.modifyById(solution);
    }

    @Override
    public void delete(Long id) {
        SolutionTemplate solution = new SolutionTemplate();
        solution.setId(id);
        solution.setDeleted(false);
        solutionRepository.modifyById(solution);
    }

    @Override
    public void modify(SolutionTemplateModifyCmd solutionModifyCmd) {
        SolutionTemplate solution = ConvertTool.convert(solutionModifyCmd, SolutionTemplate.class);
        solutionRepository.modifyById(solution);
    }

    @Override
    public SolutionTemplateRepository getRepository() {
        return solutionRepository;
    }
}
