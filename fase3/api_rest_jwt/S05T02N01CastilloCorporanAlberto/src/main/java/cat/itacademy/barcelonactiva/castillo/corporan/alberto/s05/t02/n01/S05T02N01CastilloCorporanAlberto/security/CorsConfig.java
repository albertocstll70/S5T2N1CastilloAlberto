package cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {

        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {

                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:9080", "http://localhost:9090")
                        .allowedMethods("*");

            }

        };
    }

}
