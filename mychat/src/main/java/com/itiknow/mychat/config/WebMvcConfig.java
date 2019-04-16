package com.itiknow.mychat.config;

import com.itiknow.mychat.constant.CommonConstant;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(CommonConstant.DEFAULT_USER_IMAGE_DIR+"**").addResourceLocations(CommonConstant.DEFAULT_RESOURCE_HANDLER_PREFIX+CommonConstant.DEFAULT_USER_IMAGE_STORE_DIR);
        registry.addResourceHandler(CommonConstant.DEFAULT_NEWS_ATTR_DIR+"**").addResourceLocations(CommonConstant.DEFAULT_RESOURCE_HANDLER_PREFIX+CommonConstant.DEFAULT_NEWS_ATTR_STORE_DIR);
        registry.addResourceHandler(CommonConstant.DEFAULT_NEWS_CONTENT_DIR+"**").addResourceLocations(CommonConstant.DEFAULT_RESOURCE_HANDLER_PREFIX+CommonConstant.DEFAULT_NEWS_CONTENT_STORE_DIR);
        registry.addResourceHandler(CommonConstant.DEFAULT_MESSAGE_FILE_DIR+"**").addResourceLocations(CommonConstant.DEFAULT_RESOURCE_HANDLER_PREFIX+CommonConstant.DEFAULT_MESSAGE_FILE_STORE_DIR);
        registry.addResourceHandler("/**").addResourceLocations("classpath:static/");
        super.addResourceHandlers(registry);
    }

}
