package page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import locators.mainDashLocator;

public class mainDashPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public mainDashPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
    
    public void clickOption(String option) {
    	try {
            WebElement input = wait.until(ExpectedConditions.elementToBeClickable(mainDashLocator.optionClick(option)));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", input);

            try {
                input.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", input);
            }
        } catch (Exception e) {
            System.out.println("Button not click: " + option);
        }
    }
}