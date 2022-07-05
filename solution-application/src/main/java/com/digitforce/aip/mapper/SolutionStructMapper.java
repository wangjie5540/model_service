package com.digitforce.aip.mapper;


import com.digitforce.aip.dto.cmd.SolutionTemplateAddCmd;
import com.digitforce.aip.dto.data.SolutionTemplateDTO;
import org.mapstruct.Mapper;

/**
 * TODO
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/05 13:41
 */
@Mapper
public interface SolutionStructMapper {
    SolutionTemplateDTO addCmd2DTO(SolutionTemplateAddCmd solutionAddCmd);
}
