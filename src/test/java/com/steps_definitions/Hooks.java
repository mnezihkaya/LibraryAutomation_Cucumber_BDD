package com.steps_definitions;

import com.Utils.BrowserUtils;
import com.Utils.DBUtils;
import com.Utils.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
public class Hooks {
    @Before
    public void setupDriver(Scenario scenario){
    }

    @After
    public void tearDown(Scenario scenario){
        if(scenario.isFailed()) {
            byte[] screenshot;
            screenshot = BrowserUtils.BrowserUtils.takeScreenshot("failed/" + scenario.getName());
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        DBUtils.closeConnection();
        Driver.closeDriver();
    }
}
