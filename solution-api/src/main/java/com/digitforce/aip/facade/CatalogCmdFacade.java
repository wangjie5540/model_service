package com.digitforce.aip.facade;

import com.digitforce.aip.dto.cmd.CatalogAddCmd;
import com.digitforce.aip.dto.cmd.CatalogModifyCmd;
import com.digitforce.aip.dto.cmd.CatalogStatusCmd;
import com.digitforce.framework.api.dto.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("solution")
public interface CatalogCmdFacade {

    /**
     * 添加类目
     *
     * @param catalogAddCmd
     * @return
     */
    @PostMapping("/catalog/add")
    Result add(@RequestBody CatalogAddCmd catalogAddCmd);

    /**
     * 修改类目，修改名称，排序，eClass，税编等
     *
     * @param catalogModifyCmd
     * @return
     */
    @PostMapping("/catalog/modify")
    Result modify(@RequestBody CatalogModifyCmd catalogModifyCmd);

    /**
     * 启用类目
     *
     * @param catalogStatusCmd
     * @return
     */
    @PostMapping("/catalog/on")
    Result on(@RequestBody CatalogStatusCmd catalogStatusCmd);

    /**
     * 停用类目
     *
     * @param catalogStatusCmd
     * @return
     */
    @PostMapping("/catalog/off")
    Result off(@RequestBody CatalogStatusCmd catalogStatusCmd);

}
