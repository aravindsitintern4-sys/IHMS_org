package page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import locators.billingLocator;

public class billingPage {
   WebDriver driver;
   WebDriverWait wait;

    public billingPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
	
    
    public void selectInvestigation(String investigation) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(billingLocator.investigationDropdown));
        ((JavascriptExecutor) driver)
        .executeScript("arguments[0].scrollIntoView(true);", dropdown);

((JavascriptExecutor) driver)
        .executeScript("arguments[0].click();", dropdown);
        Select select = new Select(dropdown);
        select.selectByVisibleText(investigation);
    }
    
  
    public void selectEye(String eye) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(billingLocator.eyeDropdown));
        ((JavascriptExecutor) driver)
        .executeScript("arguments[0].click();", dropdown);
        Select select = new Select(dropdown);
        select.selectByVisibleText(eye);
    }
    
    
    public void selectLocation(String location) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(billingLocator.locationDropdown));
        ((JavascriptExecutor) driver)
        .executeScript("arguments[0].click();", dropdown);
        Select select = new Select(dropdown);
        select.selectByVisibleText(location);
    }
    
    public void verifyPatientDemographyDisplayed() {
		  WebElement demography = wait.until(ExpectedConditions.visibilityOfElementLocated(billingLocator.patientDemography));
		  if (!demography.isDisplayed()) {
		      throw new AssertionError("Patient demography is NOT displayed");
		  }
		  System.out.println("Patient demography is displayed");
	} 
    
    
    public void enterInput(String input, String label) {

        WebElement enterInput = wait.until(ExpectedConditions.visibilityOfElementLocated(billingLocator.inputField(label)));
        enterInput.click();
        enterInput.clear();
        enterInput.sendKeys(input);
    }
    
    public void dropdownOptionSelect(String option, String label) {
    	WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(billingLocator.dropdownLocator(label)));
    	dropdown.click();
    	WebElement optionSelect =wait.until(ExpectedConditions.elementToBeClickable(billingLocator.optionLocator(option)));
    	optionSelect.click();
	}
    
    public void enterInputValue(String input, String label) throws InterruptedException {

        WebElement enterInput = wait.until(ExpectedConditions.elementToBeClickable(billingLocator.inputValueField(label)));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].focus();", enterInput);

        // CLEAR FIELD
        enterInput.sendKeys(Keys.CONTROL + "a");
        enterInput.sendKeys(Keys.DELETE);

        // SLOW TYPING
        for (char ch : input.toCharArray()) {
            enterInput.sendKeys(String.valueOf(ch));
            
            js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));",enterInput);
            Thread.sleep(100);
        }

        js.executeScript(
            "arguments[0].dispatchEvent(new KeyboardEvent('keyup', { bubbles: true }));" +
            "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));" +
            "arguments[0].blur();",enterInput);
    }
	
}
