package de.tramotech.restapp.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class that defines a ModelMapper bean.
 * Author: Ahmed Fikri
 */
@Configuration
public class Config {

    /**
     * Creates a new ModelMapper bean instance.
     *
     * @return The newly created ModelMapper bean instance.
     */
    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

}

