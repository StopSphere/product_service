package com.shopsphere.product_service.product_service.Config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title= "Product Service Api" ,
                version ="1.0",
                description = "API DOCS FOR PRODUCT SERVICE",
                contact = @Contact(name= "Monu Dhakad")
        )
)
public class OpenAPIConfig {
}
