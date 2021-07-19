package com.cronnoss.citrusdemoauto;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.testng.TestNGCitrusTestDesigner;
import com.consol.citrus.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Test;

public class SampleHttpRestDemo extends TestNGCitrusTestDesigner {


    @Autowired
    HttpClient user;

    @Test
    @CitrusTest
    public void testGetAllUsers() {
        http().client(user).send()
                .get()
                .contentType("application/json")
                .header("Authorization", "Basic admin@cronnoss.com admin");
        http().client(user).receive().response(HttpStatus.OK);
    }
}
