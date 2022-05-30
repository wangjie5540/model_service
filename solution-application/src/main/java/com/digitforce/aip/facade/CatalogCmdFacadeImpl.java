package com.digitforce.aip.facade;

import com.digitforce.framework.api.dto.Result;
import com.digitforce.aip.dto.cmd.CatalogAddCmd;
import com.digitforce.aip.dto.cmd.CatalogModifyCmd;
import com.digitforce.aip.dto.cmd.CatalogStatusCmd;
import com.digitforce.aip.service.cmd.CatalogCmdService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
public class CatalogCmdFacadeImpl implements CatalogCmdFacade {

    @Resource
    private CatalogCmdService catalogCmdService;

    @Override
    public Result add(@RequestBody CatalogAddCmd catalogAddCmd) {
        catalogCmdService.add(catalogAddCmd);
        return Result.success();
    }

    @Override
    public Result modify(@RequestBody CatalogModifyCmd catalogModifyCmd) {
        catalogCmdService.modify(catalogModifyCmd);
        return Result.success();
    }

    @Override
    public Result on(@RequestBody CatalogStatusCmd catalogStatusCmd) {
        return Result.success();
    }

    @Override
    public Result off(@RequestBody CatalogStatusCmd catalogStatusCmd) {
        catalogCmdService.off(catalogStatusCmd);
        return Result.success();
    }
}
