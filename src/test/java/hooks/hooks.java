package hooks;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import utils.driverFactory;

public class hooks {
	 @BeforeAll
	    public static void setup() {
	        driverFactory.initDriver("chrome");
	        driverFactory.getDriver().get("https://eyenotes20-base-qa.aravind.org:31026/login");
	    }

	    @AfterAll
	    public static void tearDown() {
//	        DriverFactory.quitDriver();
	    }
}
