package com.github.luterc.heimdallpetstore.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luter.heimdall.boot.starter.config.AbstractWebMvcConfigurer;
import com.luter.heimdall.boot.starter.util.JacksonUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 设置 url 权限拦截器
 *
 * @author Luter
 */
@Configuration
public class MvcConfig extends AbstractWebMvcConfigurer {
    /**
     * 统一 Jackson 设置
     *
     * @return the object mapper
     */
    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        return JacksonUtils.initObjectMapper(new ObjectMapper());
    }
}
