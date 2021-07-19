package com.cronnoss.citrusdemoauto;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.testng.TestNGCitrusTestDesigner;
import com.consol.citrus.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.hamcrest.Matchers.notNullValue;

public class SampleHttpRestDemo extends TestNGCitrusTestDesigner {


    @Autowired
    HttpClient user;

    @Test
    @CitrusTest
    public void testGetAllUsers() {

        String data = "";
        ClassPathResource resource = new ClassPathResource("users.json");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            data = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        http().client(user).send()
                .get()
                .header("Authorization", "Basic YWRtaW5AY3Jvbm5vc3MuY29tOmFkbWlu");
        http().client(user).receive().response(HttpStatus.OK)
                .validate("$", notNullValue())
                .validate("$", data)
                .extractFromPayload("$", "response");
        echo("${response}");
    }
}
