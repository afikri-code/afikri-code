package de.tramotech.restapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Configuration class that defines a Docket bean for Swagger documentation.
 * Author: Ahmed Fikri
 */
@Configuration
public class SpringFoxConfig {

    /**
     * Creates a new Docket bean instance for Swagger documentation.
     *
     * @return The newly created Docket bean instance.
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}

