package com.kandi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//让spring容器扫描到该类
@Configuration
@EnableSwagger2
public class Swagger2 {

    //配置swagger2核心配置  原地址  http:8080/swagger-ui.html
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)   //指定api类型为swagger2
                    .apiInfo(apiInfo())                  //用于定义api文档
                    .select().apis(RequestHandlerSelectors
                                        .basePackage("com.kandi.controller"))        //指定controller包
                                        .paths(PathSelectors.any())
                                        .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("吃货商城 api")
                .description("专为吃货商城提供的api文档")
                .version("1.0.1")
                .build();
    }
}
