package com.EventTicketBookingWebApp.EventTicketBookingWebApp.Config;



import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    OpenAPI customOpenApi() {
        return new OpenAPI().info(
                new Info()
                        .title("Ticket Booking APIs")
                        .version("v1")
                        .description("" +
                                "*Created By Sahil Rupareliya and Dakshit Ranpariya*")
                        .termsOfService("https://springdoc.org/")
                        .license(
                                new License()
                                        .name("Source Code")
                                        .url("https://github.com/dakshit-ranpariya/MovieTicketBookingWebApp")
                        )
        );
    }

}
