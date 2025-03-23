package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Log;

public class LoginPage {

    private final WebDriver driver;

    @FindBy(name = "lgn_name")
    WebElement usernameField;

    @FindBy(name = "lgn_passwd")
    WebElement passwordField;

    @FindBy(xpath = "//input[@value='LOG IN']")
    WebElement loginButton;

    @FindBy(linkText = "Forgot password?")
    WebElement forgotPasswordLink;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String username) {

        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {

        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLogin() {

        Log.info("Clicking login button..");
        loginButton.click();
    }

    public void clickForgotPassword() {
        forgotPasswordLink.click();

    }


}
