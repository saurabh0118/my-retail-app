package com.target.myretail.Configuration;

import static com.target.myretail.Common.MyRetailConstants.SWAGGER_APP_DESC;
import static com.target.myretail.Common.MyRetailConstants.SWAGGER_APP_NAME;
import static com.target.myretail.Common.MyRetailConstants.SWAGGER_DEV_EMAIL;
import static com.target.myretail.Common.MyRetailConstants.SWAGGER_DEV_NAME;
import static com.target.myretail.Common.MyRetailConstants.SWAGGER_BASE_PACKAGE;
import static com.target.myretail.Common.MyRetailConstants.SWAGGER_PRODUCT_REGEX;
import static com.target.myretail.Common.MyRetailConstants.EMPTY_STRING;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(SWAGGER_BASE_PACKAGE))
                .paths(regex(SWAGGER_PRODUCT_REGEX))
                .build()
                .apiInfo(metaData());
    }
    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                SWAGGER_APP_NAME,
                SWAGGER_APP_DESC,
                "v1.0",
                EMPTY_STRING,
                new Contact(SWAGGER_DEV_NAME, EMPTY_STRING, SWAGGER_DEV_EMAIL),
                EMPTY_STRING,
                EMPTY_STRING);
        return apiInfo;
    }
}