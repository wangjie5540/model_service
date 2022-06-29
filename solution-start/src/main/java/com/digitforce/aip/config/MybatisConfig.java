package com.digitforce.aip.config;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.digitforce.framework.context.TenantContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class MybatisConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        log.info("TenantContext.tenantId=" + JSON.toJSONString(TenantContext.tenantId()));
//        interceptor.addInnerInterceptor(new TenantLineInnerInterceptor(new LocalTenantLineHandler(Arrays.asList("t_data_resource", "we_department", "we_user", "t_system_config", "t_tenant", "t_user", "t_user_pwd", "t_permission", "t_resource", "t_role_users", "t_permission_resources", "t_role_permissions", "t_role", "dict_entry"))));
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return interceptor;
    }

}
