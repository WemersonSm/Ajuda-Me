package com.senai.ajudame.conf;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket allApi() {
		// Adding Header
		ParameterBuilder aParameterBuilder = new ParameterBuilder();
		aParameterBuilder.name("Authorization").modelRef(new ModelRef("string")).parameterType("header").required(false)
				.build();
		List<Parameter> aParameters = new ArrayList<Parameter>();
		aParameters.add(aParameterBuilder.build());

		Set<String> protocols = new HashSet<>();
		protocols.add("http");
		protocols.add("https");

		return new Docket(DocumentationType.SWAGGER_2).host("localhost:8080").groupName("All").apiInfo(apiInfo())
				.select().paths(PathSelectors.any()).build().protocols(protocols).ignoredParameterTypes(ApiIgnore.class)
				.enableUrlTemplating(true).globalOperationParameters(aParameters);
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("AJUDA-ME API").description("Sistema da Fabrica de Software ADS04")
				.termsOfServiceUrl("http://localhost:8080").license("").licenseUrl("http//localhost:8080")
				.version("2.0").build();
	}

}
