
package com.digitforce.aip.service.cmd;

import com.digitforce.framework.operation.DefaultService;
import com.digitforce.framework.api.dto.Result;
import com.digitforce.framework.api.exception.BizException;
import com.digitforce.framework.tool.ConvertTool;
import com.digitforce.aip.consts.CatalogErrorCode;
import com.digitforce.aip.domain.Catalog;
import com.digitforce.aip.dto.cmd.CatalogAddCmd;
import com.digitforce.aip.dto.cmd.CatalogModifyCmd;
import com.digitforce.aip.dto.cmd.CatalogStatusCmd;
import com.digitforce.aip.repository.CatalogRepository;
import com.digitforce.aip.validator.CatalogValidator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;


@Service
public class CatalogCmdServiceImpl extends DefaultService<Catalog> implements CatalogCmdService {
    @Resource
    private CatalogValidator catalogValidator;
    @Resource
    private CatalogRepository catalogRepository;

    @Override
    public CatalogRepository getRepository() {
        return catalogRepository;
    }

    @Override
    public void add(CatalogAddCmd cmd) {
        catalogValidator.validate(cmd.getCatalogDTO());
        Catalog addCatalog = ConvertTool.convert(cmd.getCatalogDTO(), Catalog.class);
        boolean exist = catalogRepository.isExist(addCatalog);
        if (exist) {
            throw BizException.of(CatalogErrorCode.LEVEL_ERROR);
        }
        Catalog parentCatalog = catalogRepository.getById(addCatalog.getPid());
        if (Objects.isNull(parentCatalog)) {
            throw BizException.of(CatalogErrorCode.BIZ_ERROR);
        }
        addCatalog.preAdd(parentCatalog);

        catalogRepository.save(addCatalog);
    }

    @Override
    public void modify(CatalogModifyCmd cmd) {
        catalogValidator.validate(cmd.getCatalogDTO());
        Catalog oldCatalog = catalogRepository.getById(null);
        if (Objects.isNull(oldCatalog)) {
            throw new BizException("类目不存在");
        }
        if (!oldCatalog.mayModify()) {
            throw BizException.of(CatalogErrorCode.STATUS_ERROR);
        }
        Catalog newCatalog = ConvertTool.convert(cmd.getCatalogDTO(), Catalog.class);
        newCatalog.preModify(oldCatalog);
        catalogRepository.modifyById(newCatalog);
    }

    @Override
    public void off(CatalogStatusCmd catalogStatusCmd) {

    }
}
