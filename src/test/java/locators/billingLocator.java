package locators;

import org.openqa.selenium.By;

public class billingLocator {
	
	// LOCATOR

	public static By investigationDropdown =
	        By.xpath("(//td//select)[1]");

	public static By eyeDropdown =
	        By.xpath("(//td//select)[2]");

	public static By locationDropdown =
	        By.xpath("(//td//select)[4]");
	
	public static By patientDemography = By.xpath("//app-patient-demographic");
	
	 public static By inputField(String label) {
		 return By.xpath("//label[contains(normalize-space(),'" + label + "')]" +
	        "/following::app-text-box//input[@type='text'][1]");
	 }
	 
    public static By optionLocator(String option) {
    	return By.xpath("//div[contains(@class,'cursor-pointer') and normalize-space()='" + option + "']");
    }
    
    public static By dropdownLocator(String label) { 
    	return By.xpath("//label[contains(normalize-space(),'" + label + "')]/following::div[contains(@class,'cursor-pointer')][1]");
    }
    
    public static By inputValueField(String label) {
        return By.xpath("//label[contains(normalize-space(),'" + label + "')]" +
            "/following::app-text-box//input[@type='text'][1]");
    }

    
}
