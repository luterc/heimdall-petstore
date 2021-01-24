package com.github.luterc.heimdallpetstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * The type Api doc config.
 *
 * @author Luter
 */
@Configuration
@EnableSwagger2WebMvc
public class ApiDocConfig {
    /**
     * Pet store docket.
     *
     * @return the docket
     */
    @Bean(value = "petStore")
    public Docket petStore() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("Heimdall 宠物商店 RESTful APIs")
                        .description("Heimdall 宠物商店")
                        .termsOfServiceUrl("http://www.luter.me/")
                        .version("1.0")
                        .build())
                //分组名称
                .groupName("1.0")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.github.luterc.heimdallpetstore.module"))
                .paths(PathSelectors.any())
                .build();
    }
}
