package stepDefinitions;

import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.When;
import page.mainDashPage;
import utils.driverFactory;

public class mainDashStep {

	mainDashPage mainDash;
    WebDriverWait wait;

    public mainDashStep() {
    	mainDash = new mainDashPage(driverFactory.getDriver());
        wait = new WebDriverWait(driverFactory.getDriver(), Duration.ofSeconds(10));
    }
	
	
	 @When("I click {string} option")
	    public void enterCredentials(String option) {
		 mainDash.clickOption(option);
	    }
}
