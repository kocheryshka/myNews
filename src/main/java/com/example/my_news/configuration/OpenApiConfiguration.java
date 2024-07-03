package com.example.my_news.configuration;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI openApiDescription() {

        Server localhostServer = new Server();
        localhostServer.setUrl("http://localhost:8080");
        localhostServer.setDescription("Local env");


        Contact contact = new Contact();
        contact.setName("Andrienko Vitaliy");
        contact.setEmail("vitaliy_andr@mail.ru");
        contact.setUrl("https://github.com/kocheryshka");

        License license = new License()
                .name("Apache 2.0")
                .url("https://www.apache.org/licenses/LICENSE-2.0");
        Info info = new Info()
                .title("MyNews server API")
                .version("1.0")
                .contact(contact)
                .license(license)
                .description("API for MyNews portal server");

        return new OpenAPI().info(info).servers(List.of(localhostServer));

    }
}
