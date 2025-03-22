package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.Log;

public class LoginTest extends BaseTest {

    @Test
    public void testValidLogin() throws InterruptedException {

        Log.info("Starting login test...");
        LoginPage loginPage = new LoginPage(driver);

        Log.info("Adding credentials");
        loginPage.enterUsername("admin");
        loginPage.enterPassword("Simorg2028!");
        loginPage.clickLogin();
        Thread.sleep(1000);

        Log.info("Verifying username");
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='nav-right']")).getText(),
                "Aylin Ozgeneci");
        Log.info("Verifying page title");
        Assert.assertEquals(driver.getTitle(),"Admin : admin : SIMORG");
        //commit 2

    }


}
