package stepDefinitions;

import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.And;
import page.billingPage;
import utils.driverFactory;

public class billingStep {

	billingPage billing;
    WebDriverWait wait;

    public billingStep() {
    	billing = new billingPage(driverFactory.getDriver());
        wait = new WebDriverWait(driverFactory.getDriver(), Duration.ofSeconds(10));
    }
    
    @And("I select investigation {string}")
    public void i_select_investigation(String investigation) {
    	billing.selectInvestigation(investigation);
    }
    
    @And("I select eye {string}")
    public void i_select_eye(String eye) {
    	billing.selectEye(eye);
    }
    
    @And("I select location {string}")
    public void i_select_location(String location) {
        billing.selectLocation(location);
    }
    
    
    
}
