package com.cronnoss.citrusdemoauto;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.junit.JUnit4CitrusTestRunner;
import com.consol.citrus.dsl.testng.TestNGCitrusTestRunner;
import org.testng.annotations.Test;
import utils.LoggingService;

//@Test
public class SampleJavaRunnerIT extends JUnit4CitrusTestRunner {

    LoggingService obj = new LoggingService();

    @org.junit.Test
    @CitrusTest
    public void testLogService() {
        echo("Before log called");
        obj.logs("Logs will be printed...");
        echo("After log called");
    }
}
