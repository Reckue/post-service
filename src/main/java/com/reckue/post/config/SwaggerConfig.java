package com.reckue.post.config;

import com.fasterxml.classmate.TypeResolver;
import com.reckue.post.transfer.error.ErrorResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.AlternateTypeBuilder;
import springfox.documentation.builders.AlternateTypePropertyBuilder;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.schema.AlternateTypeRuleConvention;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.schema.AlternateTypeRules.newRule;

/**
 * Class SwaggerConfig sets up settings for swagger.
 *
 * @author Kamila Meshcheryakova
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

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
                .securitySchemes(Collections.singletonList(apiKey()))
                .apiInfo(apiInfo())
                .groupName("API for post service")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.reckue.post.controller"))
                .paths(PathSelectors.regex( "/[^d].*"))
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
                        new ResponseMessageBuilder().code(401)
                                .message("UNAUTHORIZED")
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
     * Creates a customised Docket bean for debug specification.
     *
     * @return instance of the implementation of the interface Docket
     */
    @Bean
    public Docket api2(TypeResolver typeResolver) {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("debug")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.reckue.post.controller"))
                .paths(PathSelectors.ant("/debug/*"))
                .build()
                .useDefaultResponseMessages(false)
                .additionalModels(typeResolver.resolve(ErrorResponse.class))
                .globalResponseMessage(RequestMethod.GET, newArrayList(new ResponseMessageBuilder().code(404)
                                .message("NOT_FOUND")
                                .responseModel(new ModelRef("ErrorResponse"))
                                .build(),
                        new ResponseMessageBuilder().code(500)
                                .message("INTERNAL_SERVER_ERROR")
                                .responseModel(new ModelRef("ErrorResponse"))
                                .build()));
    }

	@Bean
	public AlternateTypeRuleConvention pageableConvention(final TypeResolver resolver) {
		return new AlternateTypeRuleConvention() {

			@Override
			public int getOrder() {
				return Ordered.HIGHEST_PRECEDENCE;
			}

			@Override
			public List<AlternateTypeRule> rules() {
				return Arrays.asList(
						newRule(resolver.resolve(Pageable.class), resolver.resolve(pageableMixin())),
						newRule(resolver.resolve(Map.class, LocalDate.class, LocalDate.class),
								resolver.resolve(Map.class, String.class, String.class))
						//newRule(resolver.resolve(LocalDate.class),
						//		resolver.resolve(String.class), Ordered.HIGHEST_PRECEDENCE)
				);
			}
		};
	}

	private Type pageableMixin() {
		return new AlternateTypeBuilder()
				.fullyQualifiedClassName(
						String.format("%s.generated.%s",
								Pageable.class.getPackage().getName(),
								Pageable.class.getSimpleName()))
				.withProperties(Arrays.asList(
						property(Integer.class, "page"),
						property(Integer.class, "size"),
						property(String.class, "sort")
				))
				.build();
	}

	private AlternateTypePropertyBuilder property(Class<?> type, String name) {
		return new AlternateTypePropertyBuilder()
				.withName(name)
				.withType(type)
				.withCanRead(true)
				.withCanWrite(true);
	}

    /**
     * This method is used to set up tittle and description for swagger.
     *
     * @return page swagger-ui.html with custom fields
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Post Service")
                .description("Service for posting articles about different programming languages.")
                .version("v.1.0.2")
                .contact(new Contact("Reckue", "www.reckue.com", "support@reckue.com"))
                .build();
    }

    /**
     * This method allows to add authorize button to swagger configuration.
     *
     * @return apiKey with given parameters
     */
    private ApiKey apiKey() {
        return new ApiKey("Bearer token", "Authorization", "header");
    }
}
