package riwi.com.filtrologistico.Configurations;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info =@Info(
        title="Simulacro Riwi",
        version = "1.0",
        description = "API to manage SimulacroRiwi"
))
public class OpenApiConfiguration {
}
