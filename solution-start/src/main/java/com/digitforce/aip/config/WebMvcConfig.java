package com.digitforce.aip.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * TODO
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 14:55
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.
        addResourceHandler("/swagger-ui/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
        .resourceChain(false);
  }
  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/swagger-ui/")
        .setViewName("forward:/swagger-ui/index.html");
  }
}
