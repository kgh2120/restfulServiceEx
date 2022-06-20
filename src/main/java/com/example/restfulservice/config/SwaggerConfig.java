package com.example.restfulservice.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


@EnableWebMvc
@Configuration
public class SwaggerConfig {

    private static final Contact DEFAULT_CONTACT = new Contact("이름","http://www.naver.com","kgh2120@naver.com");

    private static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Awesome Api Title",
            "My User management REST API Service", "1.0","urn:tos",DEFAULT_CONTACT,"Apache 2.0",
            "http://www.apache.org/licenses/LICENSE-2.0",new ArrayList<>());
    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<>(Arrays.asList("application/json","application/xml"));

    @Bean
    public Docket swaggerAPI(){
        //Docket : swagger Bean
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(DEFAULT_API_INFO)
                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES)
                ;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Batch Swagger")
                .description("batch execute swagger")
                .version("1.0")
                .build();
    }

}
