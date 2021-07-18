package com.cronnoss.citrusdemoauto;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.junit.JUnit4CitrusTestDesigner;
import org.junit.Test;

public class SampleJavaJunit4IT extends JUnit4CitrusTestDesigner {

    @Test
    @CitrusTest
    public void sayHelloFromJunit() {
        echo("Hello from junit 4 test framework");
    }
}
