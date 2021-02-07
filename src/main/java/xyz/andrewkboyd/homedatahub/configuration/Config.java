package xyz.andrewkboyd.etltemplate.configuration;

import org.springframework.boot.autoconfigure.web.reactive.WebFluxProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;


@Configuration
@EnableWebFlux
public class Config implements WebFluxConfigurer {

    @Bean
    public WebFluxProperties getWebFluxProperties(){
        return new WebFluxProperties();
    }
}