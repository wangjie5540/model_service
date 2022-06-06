package com.digitforce.aip.mapper;


import com.digitforce.aip.dto.cmd.SolutionAddCmd;
import com.digitforce.aip.dto.data.SolutionDTO;
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
    SolutionDTO addCmd2DTO(SolutionAddCmd solutionAddCmd);
}
