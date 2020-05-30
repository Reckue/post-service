package com.reckue.post.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Class SwaggerConfig set up settings for swagger.
 *
 * @author Kamila Meshcheryakova
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration implements WebMvcConfigurer {

    /**
     * Redirects users from home page to the Swagger UI page.
     *
     * @param registry assists with the registration of simple automated controllers pre-configured
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/swagger-ui.html");
    }

    /**
     * Creates a customised Docket bean.
     *
     * @return instance of the implementation of the interface Docket
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.reckue.post.controllers"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * This method is used to set up tittle and description for swagger.
     *
     * @return page swagger-ui.html with custom fields
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Post service API")
                .description("Service for posting articles about different programming languages.")
                .version("1.0.SNAPSHOT")
                .contact(new Contact("Reckue", "www.reckue.com", "support@reckue.com"))
                .build();
    }
}
