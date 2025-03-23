package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.EmailUtils;
import utils.ExtentReportManager;
import utils.Log;


public class BaseTest {

    protected WebDriver driver;
    protected static ExtentReports extent;
    protected ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        extent = ExtentReportManager.getReportInstance();
    }

    @AfterSuite
    public void tearDownReport() {
        extent.flush();
        String reportPath = ExtentReportManager.reportPath;
        EmailUtils.sendTestReport(reportPath);

    }

    @BeforeMethod
    public void setUp() {

        Log.info("Starting WebDriver...");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        Log.info("Navigatings to URL...");
        driver.get("https://load-test.simorg.aero/?s=login");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = ExtentReportManager.captureScreenshot(driver, "LoginFailure");
            test.fail("Test Failed.. Check Screenshot",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        }

        if (driver != null) {
            Log.info("Closing Browser...");
            driver.quit();
        }
    }

}
