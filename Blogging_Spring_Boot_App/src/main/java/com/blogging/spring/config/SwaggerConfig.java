package com.blogging.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI openApi() {
		return new OpenAPI()
				.addSecurityItem(new SecurityRequirement()
						.addList("bearerScheme"))
				.components(new Components()
					    .addSecuritySchemes("bearerScheme", new SecurityScheme()
					    		.name("bearerAcheme")
					    		.type(SecurityScheme.Type.HTTP)
					    		.bearerFormat("JWT")
					    		.scheme("bearer")))
				.info(new Info().title("Blogger App APi")
						.description("This Api developed by Manas Ranjan Tripathy.")
						.version("v1.0.0")
						.contact(new Contact().name("Manas Tripathy").email("manastripathy127@gmail.com").url("https://manas-web-dev.netlify.app/"))
						.license(new License().name("Apache2.0").url("http://springdoc.org")))
				.externalDocs(new ExternalDocumentation().description("LinkedIN Account").url("https://www.linkedin.com/in/manas-22a865196/"));
				
	}
}
