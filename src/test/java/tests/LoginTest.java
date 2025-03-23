package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ExcelUtils;
import utils.ExtentReportManager;
import utils.Log;

import java.io.IOException;

public class LoginTest extends BaseTest {

    @DataProvider(name = "LoginData")
    public Object[][] getLoginData() throws IOException {

        String filePath = System.getProperty("user.dir") + "/testdata/TestData.xlsx";
        ExcelUtils.loadExcel(filePath, "Sheet1");
        int rowCount = ExcelUtils.getRowCount();
        Object[][] data = new Object[rowCount - 1][2];

        for (int i = 1; i < rowCount; i++) {

            data[i - 1][0] = ExcelUtils.getCellData(i, 0);
            data[i - 1][1] = ExcelUtils.getCellData(i, 1);

        }
        ExcelUtils.closeExcel();
        return data;
    }

    @DataProvider(name = "LoginData2")
    public Object[][] getData() {

        return new Object[][]{
                {"user1", "pass1"},
                {"user2", "pass2"},
                {"user3", "pass3"}
        };
    }

    @Test(dataProvider = "LoginData")
    public void testValidLogin(String username, String password) throws InterruptedException {

        Log.info("Starting login test...");
        test = ExtentReportManager.createTest("Login Test - " + username);

        test.info("Navigating to URL");
        LoginPage loginPage = new LoginPage(driver);

        Log.info("Adding credentials");
        test.info("Adding Credentails");

        //loginPage.enterUsername("admin");
        //loginPage.enterPassword("Simorg2028!");
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        test.info("Clicking on Login button");
        loginPage.clickLogin();
        Thread.sleep(1000);

        Log.info("Verifying username");
        test.info("Verifying username");
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='nav-right']")).getText(),
                "Aylin Ozgeneci");
        Log.info("Verifying page title");
        test.info("Verifying page title");
        Assert.assertEquals(driver.getTitle(), "Admin : admin : SIMORG");

        test.pass("Login Successful");

    }

    @Test(dataProvider = "LoginData2")
    public void testInvalidLogin(String username, String password) throws InterruptedException {

        Log.info("Starting login test...");
        test = ExtentReportManager.createTest("Login Test - " + username);

        test.info("Navigating to URL");
        LoginPage loginPage = new LoginPage(driver);

        Log.info("Adding credentials");
        test.info("Adding Credentails");

        //loginPage.enterUsername("admin");
        //loginPage.enterPassword("Simorg2027!");
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        test.info("Clicking on Login button");
        loginPage.clickLogin();
        Thread.sleep(1000);

        Log.info("Verifying error message");
        test.info("Verifying error message");
        Assert.assertEquals(driver.findElement(By.cssSelector("#login_error_message")).getText(),
                "Username or password is incorrect !");

        test.pass("Login error message successful");

    }

    @Test
    public void testForgotPassword() {

        Log.info("Starting forgot password test...");
        test = ExtentReportManager.createTest("Forgot Password Test");

        LoginPage loginPage = new LoginPage(driver);
        test.info("Clicking on forgot password link");
        loginPage.clickForgotPassword();

        Log.info("Verifying forgot password page");
        test.info("Verifying forgot password page");
        Assert.assertEquals(driver.findElement(By.cssSelector("label[for='lgn_passwd']")).getText(), "Enter Email");

        test.pass("Forgot password test successful");

    }


}
