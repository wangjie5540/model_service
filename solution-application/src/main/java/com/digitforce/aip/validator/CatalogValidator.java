
package com.digitforce.aip.validator;

import com.digitforce.framework.api.exception.BizException;
import com.digitforce.aip.dto.data.CatalogDTO;
import org.springframework.stereotype.Component;


@Component
public class CatalogValidator {


    public void validate(CatalogDTO catalog) {

        throw new BizException("");
    }
}
