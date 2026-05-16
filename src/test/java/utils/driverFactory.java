package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class driverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private static final String DOWNLOAD_DIR =
            System.getProperty("user.dir") + File.separator + "downloads";

 // BROWSER LAUNCH ACTION
    public static void initDriver(String browser) {

        if (driver.get() == null) {

            createDownloadDirectory();

            WebDriver webDriver;

            switch (browser.toLowerCase()) {

                case "chrome":
                    webDriver = new ChromeDriver(getChromeOptions());
                    break;
               
                case "firefox":
                    webDriver = new FirefoxDriver(getFirefoxOptions());
                    break;
                

                default:
                    throw new RuntimeException("Browser not supported: " + browser);
            }

            driver.set(webDriver);

            getDriver().manage().window().maximize();
        }
    }

    // FILE DOWNLOAD DIRECTORY SETTING
    private static void createDownloadDirectory() {
        File dir = new File(DOWNLOAD_DIR);

        if (!dir.exists()) {
            boolean created = dir.mkdirs();
            if (created) {
                System.out.println("Download directory created: " + DOWNLOAD_DIR);
            }
        }
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static String getDownloadPath() {
        return DOWNLOAD_DIR;
    }

    public static void quitDriver() {

        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
    
    // CHROME SETTING BEFOR DOWNLOAD
    private static ChromeOptions getChromeOptions() {

        Map<String, Object> prefs = new HashMap<>();
        
        // SAVE ALL THE FILES IN THIS PROJECT FOLDER
        prefs.put("download.default_directory", DOWNLOAD_DIR);
        
       // SOMETIMES CONFIRM "SAVE AS" POPUP APPEARS THIS CODE FOR SKIP THAT PROCESS
        prefs.put("download.prompt_for_download", false);
        
       // SAVE THE DOWNLOADED FILES IN SAME FOLDER
        prefs.put("download.directory_upgrade", true);
        
       // ALLOW DOWNLOADS WITHOUT BLOCKING (DON'T BLOCK DOWNLOAD)
        prefs.put("safebrowsing.enabled", true);

       // CHROME OPENS WITH MY CUSTOM SETTINGS
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);

        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");
        options.addArguments("--start-maximized");

        return options;
    }
    
    private static FirefoxOptions getFirefoxOptions() {

        FirefoxProfile profile = new FirefoxProfile();

        // DOWNLOAD SETTING (SAME LIKE CHROME BUT IT HAS DIFFERENT KEYS)
        profile.setPreference("browser.download.dir", DOWNLOAD_DIR);
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk","application/pdf,application/octet-stream,application/vnd.ms-excel");
        profile.setPreference("pdfjs.disabled", true);

        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(profile);

        // FIREFOX HAS DIFFERENT SIZE SO WEF IX THE SIZE VIA ADD ARGUMENTS
        options.addArguments("--width=1920");
        options.addArguments("--height=1080");
        
        return options;
    }
}