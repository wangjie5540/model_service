
package com.digitforce.aip.service.cmd;

import com.digitforce.framework.operation.CrudOperation;
import com.digitforce.framework.api.dto.Result;
import com.digitforce.aip.domain.Catalog;
import com.digitforce.aip.dto.cmd.CatalogAddCmd;
import com.digitforce.aip.dto.cmd.CatalogModifyCmd;
import com.digitforce.aip.dto.cmd.CatalogStatusCmd;


public interface CatalogCmdService extends CrudOperation<Catalog> {

    void add(CatalogAddCmd cmd);

    void modify(CatalogModifyCmd cmd);

    void off(CatalogStatusCmd catalogStatusCmd);
}
