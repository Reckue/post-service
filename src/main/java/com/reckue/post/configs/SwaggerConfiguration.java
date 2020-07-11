package com.reckue.post.configs;

import com.fasterxml.classmate.TypeResolver;
import com.reckue.post.transfers.errors.ErrorResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Class SwaggerConfig sets up settings for swagger.
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
    public Docket api(TypeResolver typeResolver) {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.reckue.post.controllers"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .additionalModels(typeResolver.resolve(ErrorResponse.class))
                .globalResponseMessage(RequestMethod.POST, newArrayList(new ResponseMessageBuilder().code(409)
                                .message("CONFLICT")
                                .responseModel(new ModelRef("ErrorResponse"))
                                .build(),
                        new ResponseMessageBuilder().code(500)
                                .message("INTERNAL_SERVER_ERROR")
                                .responseModel(new ModelRef("ErrorResponse"))
                                .build()))
                .useDefaultResponseMessages(false)
                .additionalModels(typeResolver.resolve(ErrorResponse.class))
                .globalResponseMessage(RequestMethod.PUT, newArrayList(new ResponseMessageBuilder().code(400)
                                .message("BAD_REQUEST")
                                .responseModel(new ModelRef("ErrorResponse"))
                                .build(),
                        new ResponseMessageBuilder().code(404)
                                .message("NOT_FOUND")
                                .responseModel(new ModelRef("ErrorResponse"))
                                .build(),
                        new ResponseMessageBuilder().code(500)
                                .message("INTERNAL_SERVER_ERROR")
                                .responseModel(new ModelRef("ErrorResponse"))
                                .build()))
                .useDefaultResponseMessages(false)
                .additionalModels(typeResolver.resolve(ErrorResponse.class))
                .globalResponseMessage(RequestMethod.GET, newArrayList(new ResponseMessageBuilder().code(400)
                                .message("BAD_REQUEST")
                                .responseModel(new ModelRef("ErrorResponse"))
                                .build(),
                        new ResponseMessageBuilder().code(404)
                                .message("NOT_FOUND")
                                .responseModel(new ModelRef("ErrorResponse"))
                                .build(),
                        new ResponseMessageBuilder().code(500)
                                .message("INTERNAL_SERVER_ERROR")
                                .responseModel(new ModelRef("ErrorResponse"))
                                .build()))
                .useDefaultResponseMessages(false)
                .additionalModels(typeResolver.resolve(ErrorResponse.class))
                .globalResponseMessage(RequestMethod.DELETE, newArrayList(new ResponseMessageBuilder().code(404)
                                .message("NOT_FOUND")
                                .responseModel(new ModelRef("ErrorResponse"))
                                .build(),
                        new ResponseMessageBuilder().code(500)
                                .message("INTERNAL_SERVER_ERROR")
                                .responseModel(new ModelRef("ErrorResponse"))
                                .build()));
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
                .version("SNAPSHOT-1.0.2")
                .contact(new Contact("Reckue", "www.reckue.com", "support@reckue.com"))
                .build();
    }
}
