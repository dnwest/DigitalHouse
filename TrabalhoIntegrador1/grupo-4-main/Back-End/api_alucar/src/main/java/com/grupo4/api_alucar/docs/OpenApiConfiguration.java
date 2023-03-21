package com.grupo4.api_alucar.docs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfiguration {
    @Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
			.info(new Info()
				.title("Alucar API")
				.version("v1")
				.description("The Alucar API provides car rental services.")
				.termsOfService("https://alucar.com/terms")
				.license(
					new License()
						.name("Apache 2.0")
						.url("https://alucar.com/license")
					)
				);
	}
}
