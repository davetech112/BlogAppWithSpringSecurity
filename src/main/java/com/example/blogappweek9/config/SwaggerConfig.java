package com.example.blogappweek9.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration

public class SwaggerConfig {

    @Value("v1")
    private String version;

    @Bean
    public OpenAPI api(){
        SecurityScheme securityScheme  = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");


        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("jwt",securityScheme))
                .info(new Info()
                        .title("Fashion Blog API")
                        .description("Api that provides crud operations for a fashion blog.")
                        .version(version))
                .security(Collections.singletonList(new SecurityRequirement().addList("jwt")));
    }


    @Bean
    public GroupedOpenApi usersEndpoint(){
        return  GroupedOpenApi
                .builder()
                .group("Users")
                .pathsToMatch("/api/v1/auth/**").build();
    }
    @Bean
    public GroupedOpenApi LikeEndpoint(){
        return  GroupedOpenApi
                .builder()
                .group("Like")
                .pathsToMatch("/api/v1/like/**").build();
    }
    @Bean
    public GroupedOpenApi commentEndpoint(){
        return  GroupedOpenApi
                .builder()
                .group("Comment")
                .pathsToMatch("/api/v1/comment/**").build();
    }


    @Bean
    public GroupedOpenApi postEndpoint(){
        return  GroupedOpenApi
                .builder()
                .group("Post")
                .pathsToMatch("/api/v1/post/**").build();
    }
    @Bean
    public GroupedOpenApi adminEndpoint(){
        return  GroupedOpenApi
                .builder()
                .group("Admin")
                .pathsToMatch("/api/v1/admin/**").build();
    }


}