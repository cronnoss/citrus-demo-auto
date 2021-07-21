package com.cronnoss.citrusdemoauto;

import com.consol.citrus.annotations.CitrusEndpoint;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.testng.TestNGCitrusTestDesigner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.http.config.annotation.HttpClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.hamcrest.Matchers.notNullValue;

public class SampleHttpRestDemo extends TestNGCitrusTestDesigner {


    //@Autowired
    @CitrusEndpoint
    @HttpClientConfig(requestUrl = "http://localhost:8080/api/users")
    HttpClient user;

    @Test(priority = 0/*, enabled = false*/)
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
                //TODO Think. Need show fields every time in one order in project Bootjavama. Now the "USER" and "ADMIN" fields are swapped in response.
                //.validate("$", data)
                .extractFromPayload("$", "response");
        echo("${response}");
    }

    @Test(priority = 1/*, enabled = false*/)
    @CitrusTest
    public void testGetUserById() {

        String data = "";
        ClassPathResource resource = new ClassPathResource("user.json");
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
                .get("/1")
                .header("Authorization", "Basic YWRtaW5AY3Jvbm5vc3MuY29tOmFkbWlu");
        http().client(user).receive().response(HttpStatus.OK)
                .validate("$", notNullValue())
                .validate("$", data)
                .extractFromPayload("$", "response");
        echo("${response}");
    }

    @Test(priority = 2/*, enabled = false*/)
    @CitrusTest
    public void testAddUser() {

        String data = "";
        ClassPathResource resource = new ClassPathResource("userAdd.json");
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
                .post()
                .contentType("application/json")
                .payload(resource)
                .header("Authorization", "Basic YWRtaW5AY3Jvbm5vc3MuY29tOmFkbWlu");
        http().client(user).receive().response(HttpStatus.CREATED)
                .extractFromPayload("$", "response");
        echo("${response}");
    }

    @Test(priority = 3/*, enabled = false*/)
    @CitrusTest
    public void testDeleteUser() {

        http().client(user).send()
                .delete("/3")
                .header("Authorization", "Basic YWRtaW5AY3Jvbm5vc3MuY29tOmFkbWlu");
        http().client(user).receive().response(HttpStatus.NO_CONTENT)
                .payload("");
    }
}
