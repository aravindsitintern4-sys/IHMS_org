package stepDefinitions;

import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
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
    
    @Then("Patient demography is displayed")
    public void patient_demography_display() {
        billing.verifyPatientDemographyDisplayed();
    }                       
    
//    FEES AND AMOUNT INPUT FIELD
    @And("I enter {string} on input field {string}")
    public void enter_input(String input, String label) {
        billing.enterInput(input,label);
    }
    
//	 DROPDOWN WITH NPUT FIELD (LIKE NG-DROPDOWN)
	 @And("I select dropdown value as {string} from {string} label")
	 public void select_dropdown_value_from_label(String option, String label) {
		 billing.dropdownOptionSelect(option,label); 
	 }
	 
//	 FORCE INPUT (CORPORATE INPUT FIELD)
	 @And("I enter input value as {string} on input field {string}")
	 public void enter_input_value_for_label_force_input(String input, String label) throws InterruptedException {
	      billing.enterInputValue(input,label);
	 }
    
    
}
