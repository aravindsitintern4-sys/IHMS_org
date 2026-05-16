package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import page.loginPage; 
import utils.driverFactory;

public class loginSteps {

    private WebDriver driver = driverFactory.getDriver();
    private loginPage loginPage = new loginPage(driver);

    @Given("initially on login page")
    public void user_is_on_login_page() {
        driverFactory.getDriver().get("https://eyenotes20-base-qa.aravind.org:31026/login");
    }

    @When("I enter username {string} and password {string}")
    public void enterCredentials(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @Then("I navigated to ihms dashboard")
    public void user_navigated_to_dashboard() {
        Assert.assertTrue(loginPage.isDashboardLoaded(), "User was NOT navigated to the dashboard!");
    }
}