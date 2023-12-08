package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{

    public AccountRegistrationPage(WebDriver driver) {
        super(driver);
    }

    //elements
    @FindBy(name = "firstname")
    WebElement txtFirstName;

    @FindBy(name = "lastname")
    WebElement txtLastName;

    @FindBy(name = "email")
    WebElement txtEmail;

    @FindBy(name = "telephone")
    WebElement txtTelephone;

    @FindBy(name = "password")
    WebElement txtPassword;

    @FindBy(name = "confirm")
    WebElement txtConfirmPassword;

    @FindBy(name = "agree")
    WebElement chkPolicy;

    @FindBy(xpath = "//input[@value='Continue']")
    WebElement btnContinue;

    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement msgConfirmation;

    //action methods
    public void setFirstName(String fName) {
        txtFirstName.sendKeys(fName);
    }
    public void setLastName(String lName) {
        txtLastName.sendKeys(lName);
    }
    public void setEmail(String email) {
        txtEmail.sendKeys(email);
    }
    public void setTelephone(String tel) {
        txtTelephone.sendKeys(tel);
    }
    public void setPassword(String pass) {
        txtPassword.sendKeys(pass);
    }
    public void setConfirmPassword(String conf) {
        txtConfirmPassword.sendKeys(conf);
    }
    public void clickPrivacyPolicy() {
        chkPolicy.click();
    }
    public void clickContinue() {
        btnContinue.click();
    }

    public String getConfirmationMessage() {
        try {
            return msgConfirmation.getText();
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }
}
