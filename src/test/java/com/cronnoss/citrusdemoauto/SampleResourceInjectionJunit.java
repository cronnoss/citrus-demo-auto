package com.cronnoss.citrusdemoauto;

import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.design.TestDesigner;
import com.consol.citrus.dsl.junit.JUnit4CitrusTest;
import com.consol.citrus.dsl.runner.TestRunner;
import org.junit.Test;


public class SampleResourceInjectionJunit extends JUnit4CitrusTest {

    @CitrusTest
    @Test
    public void injectDesigner(@CitrusResource TestDesigner designer) {
        designer.echo("Before junit");
        System.out.println("Its a print statement");
        designer.echo("After");
    }

    @CitrusTest
    @Test
    public void injectRunner(@CitrusResource TestRunner runner) {
        runner.echo("Before junit");
        System.out.println("Its a print statement");
        runner.echo("After");
    }
}
