package com.digitforce.aip.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.digitforce.aip.dto.data.TableSelection;
import com.digitforce.aip.enums.ServingTypeEnum;
import com.digitforce.framework.domain.AggregateRoot;
import lombok.Data;

import java.util.List;

/**
 * 方案服务持久化类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/05 13:55
 */
@Data
public class SolutionServingPO extends AggregateRoot<Long> {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long solutionId;
    private Long templateId;
    private List<TableSelection> selection;
    private ServingTypeEnum servingType;
}
