package com.cronnoss.citrusdemoauto;

import com.consol.citrus.dsl.runner.AbstractTestBehavior;

public class AfterLogBehavior extends AbstractTestBehavior {

    @Override
    public void apply() {
        echo("After the actions execute citrus:currentDate()");
    }
}
