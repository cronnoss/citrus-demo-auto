package com.cronnoss.citrusdemoauto;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.testng.TestNGCitrusTestDesigner;
import com.consol.citrus.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class SampleHttpRestDemo extends TestNGCitrusTestDesigner {


    @Autowired
    HttpClient user;

    @Test
    @CitrusTest
    public void testGetAllUsers() {
        http().client(user).send()
                .get()
                .header("Authorization", "Basic YWRtaW5AY3Jvbm5vc3MuY29tOmFkbWlu");
        http().client(user).receive().response(HttpStatus.OK)
                .validate("$", notNullValue())
                .validate("$", "{\"_embedded\":{\"users\":[{\"firstName\":\"User_First\",\"lastName\":\"User_Last\",\"_links\":{\"self\":{\"href\":\"http:\\/\\/localhost:8080\\/api\\/users\\/1\"},\"user\":{\"href\":\"http:\\/\\/localhost:8080\\/api\\/users\\/1\"}},\"roles\":[\"USER\"],\"email\":\"user@gmail.com\"},{\"firstName\":\"Admin_First\",\"lastName\":\"Admin_Last\",\"_links\":{\"self\":{\"href\":\"http:\\/\\/localhost:8080\\/api\\/users\\/2\"},\"user\":{\"href\":\"http:\\/\\/localhost:8080\\/api\\/users\\/2\"}},\"roles\":[\"USER\",\"ADMIN\"],\"email\":\"admin@cronnoss.com\"}]},\"_links\":{\"search\":{\"href\":\"http:\\/\\/localhost:8080\\/api\\/users\\/search\"},\"profile\":{\"href\":\"http:\\/\\/localhost:8080\\/api\\/profile\\/users\"},\"self\":{\"href\":\"http:\\/\\/localhost:8080\\/api\\/users\"}},\"page\":{\"number\":0,\"size\":20,\"totalPages\":1,\"totalElements\":2}}")
                .extractFromPayload("$", "response");
        echo("${response}");
    }
}
