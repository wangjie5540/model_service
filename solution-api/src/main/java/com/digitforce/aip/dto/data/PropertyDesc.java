package com.digitforce.aip.dto.data;

import com.digitforce.aip.enums.DataTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyDesc {
    private String name;
    private String cname;
    private DataTypeEnum dataType;
}
