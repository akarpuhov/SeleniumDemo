package stepdef;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.MyWebDriver;

public class ScenarioHooks {

    @Before
    public void beforeScenario(){

    }

    @After
    public void afterScenario(){
        MyWebDriver.kill();
    }
}
