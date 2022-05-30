package com.digitforce.aip.dto.cmd;

import com.digitforce.framework.api.dto.Command;
import com.digitforce.aip.dto.data.CatalogDTO;
import lombok.Data;

@Data
public class CatalogModifyCmd extends Command {

    private CatalogDTO catalogDTO;

}
