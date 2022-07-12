package com.digitforce.aip.service.cmd;

import com.digitforce.aip.domain.SolutionTemplate;
import com.digitforce.aip.dto.cmd.SolutionTemplateAddCmd;
import com.digitforce.aip.dto.cmd.SolutionTemplateModifyCmd;
import com.digitforce.aip.mapper.SolutionTemplateMapper;
import com.digitforce.aip.repository.SolutionTemplateRepository;
import com.digitforce.aip.validator.SolutionValidator;
import com.digitforce.framework.operation.DefaultService;
import com.digitforce.framework.tool.ConvertTool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 方案模板命令接口类实现类
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
    private SolutionTemplateRepository solutionTemplateRepository;
    @Resource
    private SolutionTemplateMapper solutionTemplateMapper;

    @Override
    public void add(SolutionTemplateAddCmd solutionAddCmd) {
//        solutionValidator.validate(null);
        SolutionTemplate solutionTemplate = ConvertTool.convert(solutionAddCmd, SolutionTemplate.class);
        solutionTemplateRepository.save(solutionTemplate);
    }

    @Override
    public void on(Long id) {
        SolutionTemplate solutionTemplate = new SolutionTemplate();
        solutionTemplate.setId(id);
        solutionTemplate.setStatus(true);
        solutionTemplateRepository.modifyById(solutionTemplate);
    }

    @Override
    public void batchOn(List<Long> ids) {
        List<SolutionTemplate> solutionTemplateList = ids.stream().map(id -> {
            SolutionTemplate solutionTemplate = new SolutionTemplate();
            solutionTemplate.setId(id);
            solutionTemplate.setStatus(true);
            return solutionTemplate;
        }).collect(Collectors.toList());
        solutionTemplateRepository.modifyBatchById(solutionTemplateList);
    }

    @Override
    public void off(Long id) {
        SolutionTemplate solution = new SolutionTemplate();
        solution.setId(id);
        solution.setStatus(false);
        solutionTemplateRepository.modifyById(solution);
    }

    @Override
    public void batchOff(List<Long> ids) {
        List<SolutionTemplate> solutionTemplateList = ids.stream().map(id -> {
            SolutionTemplate solutionTemplate = new SolutionTemplate();
            solutionTemplate.setId(id);
            solutionTemplate.setStatus(false);
            return solutionTemplate;
        }).collect(Collectors.toList());
        solutionTemplateRepository.modifyBatchById(solutionTemplateList);
    }

    @Override
    public void delete(Long id) {
        solutionTemplateRepository.removeById(id);
    }

    @Override
    public void batchDelete(List<Long> ids) {
        solutionTemplateRepository.removeByIds(ids);
    }

    @Override
    public void modify(SolutionTemplateModifyCmd solutionModifyCmd) {
        SolutionTemplate solution = ConvertTool.convert(solutionModifyCmd, SolutionTemplate.class);
        solutionTemplateRepository.modifyById(solution);
    }

    @Override
    public void batchModify(List<SolutionTemplate> solutionTemplateList) {
        solutionTemplateRepository.modifyBatchById(solutionTemplateList);
    }

    @Override
    public void browseCountInc(Long id) {
        solutionTemplateMapper.browseCountInc(id);
    }

    @Override
    public void applyCountInc(Long id) {

    }

    @Override
    public SolutionTemplateRepository getRepository() {
        return solutionTemplateRepository;
    }
}
