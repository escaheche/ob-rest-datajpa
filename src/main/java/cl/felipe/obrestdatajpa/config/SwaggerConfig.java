package cl.felipe.obrestdatajpa.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración Swagger para la generacion de documentacion de la API REST
 * HTML: http://localhost:8081/swagger-ui/
 */
@Configuration
public class SwaggerConfig{

    @Bean
    public OpenAPI api(){
        return new OpenAPI()
                .info(new Info()
                        .title("Demo Felipe Martinez Spring Boot 3 API")
                        .version("1.0").description("Ejemplo app Spring Boot 3 con Swagger")
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));


    }

}
