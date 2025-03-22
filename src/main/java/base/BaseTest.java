package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.Log;


public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {

        Log.info("Starting WebDriver...");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        Log.info("Navigating to URL...");
        driver.get("https://load-test.simorg.aero/?s=login");
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            Log.info("Closing Browser...");
            driver.quit();
        }
    }

}
