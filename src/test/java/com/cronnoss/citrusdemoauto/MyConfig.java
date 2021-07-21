package com.cronnoss.citrusdemoauto;

import com.consol.citrus.dsl.endpoint.CitrusEndpoints;
import com.consol.citrus.http.client.HttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    @Bean
    public HttpClient client() {
        System.out.println("Client is created");
        return CitrusEndpoints.http().client()
                .requestUrl("http://localhost:8080/api/users")
                .build();
    }
}
