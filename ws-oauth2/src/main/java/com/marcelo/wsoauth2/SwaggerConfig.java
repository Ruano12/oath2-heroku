package com.marcelo.wsoauth2;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static springfox.documentation.builders.PathSelectors.regex;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.Contact;

@Configuration
@EnableSwagger2
public class SwaggerConfig{
	
	private static final String API_INFO_TITLE = "SWAGGER EXAMPLE";
	 private static final String API_INFO_DESC = "Documentação da API";
	 private static final String API_INFO_TERMS_URL = "http://localhost:8080/";
	 private static final String API_INFO_CONTACT = "marcelo@hotmail.com";
	 private static final String API_INFO_LICENSE = "Proprietária";
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("API")
				.apiInfo(apiInfo())
				.directModelSubstitute(LocalDateTime.class, Date.class).select()
				.paths(PathSelectors.regex("/api.*"))
				 .build();
	}
	
	private static ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(API_INFO_TITLE)
                .description(API_INFO_DESC)
                .termsOfServiceUrl(API_INFO_TERMS_URL)
                .contact(new Contact("Example", API_INFO_TERMS_URL,
                        API_INFO_CONTACT))
                .license(API_INFO_LICENSE).build();
    }
}