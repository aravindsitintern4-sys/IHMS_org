package locators;

import org.openqa.selenium.By;

public class mainDashLocator {
	    
	    public static By optionClick(String option) {
	    	return By.xpath("//h3[contains(text(), '" + option + "')]");
	    }
	    
	    
	    
}