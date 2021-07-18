package com.cronnoss.citrusdemoauto;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.testng.TestNGCitrusTestRunner;
import org.testng.annotations.Test;

public class SampleCitrusFunctionsDemoIT extends TestNGCitrusTestRunner {

    @CitrusTest
    @Test(enabled = false)
    public void testStringFunctions() {

        variable("uname", "Cronnoss Admin");
        echo("My name is citrus:substring(${uname},0,9)");
        echo(" citrus:concat('My name is ',${uname})");
        echo(" ${uname} has length => citrus:stringLength(${uname})");
    }

    @CitrusTest
    @Test(enabled = false)
    public void testNumberFunctions() {

        echo("My name is citrus:maximum(12,0,8)");
        echo(" citrus:sum(1,2,3,4,5,6,7)");
        echo(" citrus:randomNumber(3)");
    }

    @CitrusTest
    @Test
    public void testJsonFunctions() {

        variable("person", "{\"name\":\"cronnoss\",\"city\":\"Moscow\"}");
        echo("JSON Object => ${person}");
        echo("Name : citrus:jsonPath(${person}, '$.name')");
    }

}
