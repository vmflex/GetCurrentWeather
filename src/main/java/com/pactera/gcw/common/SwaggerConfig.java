package com.pactera.gcw.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.ModelMap;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
        			.ignoredParameterTypes(ModelMap.class)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.nowbook.mm.common"))
                .paths(apiPaths())
                .build();
    }

    @SuppressWarnings("unchecked")
	private Predicate<String> apiPaths() {
        return Predicates.or(PathSelectors.regex("/common-api/.*"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("MM Common API")
                .description("The online reference documentation for developers")
                .version("1.0")
                .build();
    }

}
