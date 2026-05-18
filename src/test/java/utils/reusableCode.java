package utils;

import java.io.File;
import java.time.Duration;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class reusableCode {

	   WebDriver driver;
	   WebDriverWait wait;

	   public reusableCode() {
		   this.driver = driverFactory.getDriver();
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	    }
   
// ALERT ERROR MESSAGE DISPLAY
    @Then("{string} toast should be displayed")
    public void error_toast_should_be_displayed(String expectedMsg) {
        // LOCATOR
        By toastMsg = By.xpath("//div[@role='alert'] | //app-toast//span");

        // ACTION
        WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(toastMsg));
        String actualMsg = toast.getText().trim();

        // VALIDATION
        if (actualMsg.equals(expectedMsg)) {
            System.out.println("Toast Message Matched : " + actualMsg);
        } else {
            System.out.println("Expected Message : " + expectedMsg);
            System.out.println("Actual Message : " + actualMsg);
        }
    }
    
    
 // POP UP DISPLAY VERIFICATION  
    @Then("popup {string} should be displayed")
    public void popup_displayed(String popupName) {
        // LOCATOR
        By popupMsg = By.xpath(
                "//div[contains(@class,'modal') or contains(@class,'popup') or contains(@class,'container')]//*[normalize-space()='"
                        + popupName + "']");
        // ACTION
        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(popupMsg));
        // VALIDATION
        if (popup.isDisplayed()) {
            System.out.println("Popup is displayed : " + popupName);
        } else {
            System.out.println("Popup is not displayed : " + popupName);
        }
    }
    
    
 // SCREENSHOT

    @Then("screenshot should be captured {string}")
    public void take_screenshot(String fileName) {
        try {
            Thread.sleep(4000);
            TakesScreenshot ts = (TakesScreenshot) driverFactory.getDriver();

            File src = ts.getScreenshotAs(OutputType.FILE);
            
            String path =System.getProperty("user.dir")+ "\\screenshots\\";
            File dir = new File(path);

            if (!dir.exists()) {
                dir.mkdirs();
            }

            File dest = new File(path + fileName + ".png");
            FileHandler.copy(src, dest);
            
            System.out.println("Screenshot saved at : " + dest.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
 // FILE DOWNLOAD

    @Then("file should be downloaded successfully")
    public void verify_file_download() {

        String path =System.getProperty("user.home") + "\\Downloads";

        File dir = new File(path);
        boolean downloaded = false;
        
        int timeout = 20;
        for (int i = 0; i < timeout; i++) {
            File[] files = dir.listFiles();
            if (files != null && files.length > 0) {
                for (File file : files) {
                   System.out.println("Found file : " + file.getName());

                    // IGNORE CHROME TEMP FILE
                    if (!file.getName().endsWith(".crdownload")) {
                        downloaded = true;
                        break;
                    }
                }
            }
            if (downloaded) {
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Assert.assertTrue(downloaded, "File not downloaded!");
        System.out.println("File downloaded successfully");
    }
    
 
 // BUTTON CLICK COMMON
    @And("I click {string} button")
    public void click_button(String btn) {
        try {
            // LOCATOR
            By button = By.xpath("//button[normalize-space()='"+ btn + "']");

            // FIND ELEMENT
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(button));

            // SCROLL TO ELEMENT
            ((JavascriptExecutor) driverFactory.getDriver()).executeScript("arguments[0].scrollIntoView({block:'center'});",element);

            // CHECK ENABLED
            if (!element.isEnabled()) {
            	throw new RuntimeException(btn + " button is disabled");
            }
            // CLICK ACTION
            try {
                wait.until(ExpectedConditions.elementToBeClickable(button)).click();
            } catch (Exception e) {
                try {
                    element = wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(button)));
                    element.click();
                } catch (Exception ex) {
                    ((JavascriptExecutor) driverFactory.getDriver()).executeScript("arguments[0].click();",element);
                }
            }
            System.out.println(btn + " button clicked successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
// CLICK AND SELECT MAIN OPTIONS AND SUB OPTIONS  
    @And("I click {string} menu and select {string}")
    public void click_menu_and_select_submenu(String mainMenu, String subMenu) {

        WebDriver driver = driverFactory.getDriver();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        Actions actions = new Actions(driver);

        JavascriptExecutor js = (JavascriptExecutor) driver;

        // SWITCH TO ACTIVE WINDOW
        for (String window : driver.getWindowHandles()) {
            driver.switchTo().window(window);
        }

        // MAIN MENU
        WebElement menu = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[normalize-space()='" + mainMenu + "']")));

        // SCROLL
        js.executeScript("arguments[0].scrollIntoView(true);", menu);

        // HOVER
        actions.moveToElement(menu).perform();

        // SUB MENU
        WebElement submenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='" + subMenu + "']")));

        // HOVER SUBMENU
        actions.moveToElement(submenu).perform();

        // CLICK SUBMENU
        js.executeScript("arguments[0].click();", submenu);
    }
    
    
//   ENTER INPUT WITH LABEL
    @And("I enter {string} in {string} label")
    public void enter_input(String value, String label) {

        By inputLocator = By.xpath("//label[contains(normalize-space(),'" + label + "')]" +
                "/following::input[1]");

        WebElement enterInput = wait.until(ExpectedConditions.visibilityOfElementLocated(inputLocator));
        enterInput.clear();
        enterInput.sendKeys(value);
    }
    
// SELECT OPTION FROM DROPDOWN WITH LABEL
    @And("I select {string} from {string} label")
    public void select_dropdown_with_label(String option, String label) {

    	By selectLocator = By.xpath(
                "//label[contains(normalize-space(),'" + label + "')]" +
                "/following::select[1]");

        WebElement dropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(selectLocator));

        Select select = new Select(dropdown);

        select.selectByVisibleText(option);
    }
    
//   KEYBORD ACTIONS (ENTER,TAB)
    @And("I press {string} key")
    public void keyboard_action(String key) {

    	Actions actions = new Actions(driver);

        // KEYBOARD ACTION
        if (key.equalsIgnoreCase("ENTER")) {
            actions.sendKeys(Keys.ENTER).perform();
        }
        else if (key.equalsIgnoreCase("TAB")) {
            actions.sendKeys(Keys.TAB).perform();
        }
    }
    
   
    
}