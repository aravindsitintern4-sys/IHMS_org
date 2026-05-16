package page;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import locators.loginLocator;

public class loginPage {

	   WebDriver driver;
	   WebDriverWait wait;

	    public loginPage(WebDriver driver) {
	        this.driver = driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	    }
    
    

 // USER NAME INPUT FIELD
     public void enterUsername(String user) {
         WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(loginLocator.username));
 		element.clear();
 		element.sendKeys(user);
     }

 // PASSWORD INPUT FIELD
     public void enterPassword(String pass) {
         WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(loginLocator.password));
 		element.clear();
 		element.sendKeys(pass);
     }

// MOVE TO DASHBOARD
    public boolean isDashboardLoaded() {
        try {
            return wait.until(ExpectedConditions.urlContains("dashboard"));
        } catch (Exception e) {
            return false;
        }
    }
}
