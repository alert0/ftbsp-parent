package com.friendtimes.user.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName SwaggerConfig
 * @Author Yjfqb
 * @CreteDate 2019/3/22 14:53
 * @Description
 * @Version 1.0 文档生成器配置
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(Constants.Swagger.BASE_SCAN_PACKGE))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(Constants.Swagger.TITLE)
                .description(Constants.Swagger.DESCRIPTION)
                .termsOfServiceUrl(Constants.Swagger.URL)
                .contact(Constants.Swagger.CONTACT)
                .version(Constants.Swagger.VERSION)
                .build();
    }
}
