package stepdef;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.ATWebDriver;

public class ScenarioHooks {

    @Before
    public void beforeScenario(){

    }

    @After
    public void afterScenario(){
        ATWebDriver.kill();
    }
}
