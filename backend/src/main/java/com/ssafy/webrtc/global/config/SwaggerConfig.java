package com.ssafy.webrtc.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                // Swagger에서 제공하는 기본 응답 코드(200, 401, ... )
                .useDefaultResponseMessages(false)
                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.ssafy.webrtc.domain"))
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    // Swagger로 노출할 정보들
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("CatchPrize User Server")
                .description("CatchPrize User Server API")
                .version("1.0")
                .build();
    }

}
