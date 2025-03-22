package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Log;

public class LoginPage {

    private final WebDriver driver;

    private final By usernameField = By.name("lgn_name");
    private final By passwordField = By.name("lgn_passwd");
    private final By loginButton = By.cssSelector("input[value='LOG IN']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username) {

        driver.findElement(usernameField).clear();
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {

        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {

        Log.info("Clicking login button..");
        driver.findElement(loginButton).click();
    }


}
