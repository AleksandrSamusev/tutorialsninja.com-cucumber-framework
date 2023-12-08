package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {
    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h2[normalize-space()='My Account']")
    WebElement msgConfirmation;

    @FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Logout']")
    WebElement btnLogout;

    public boolean isMyAccountPageExists() {
        try {
            return msgConfirmation.isDisplayed();
        } catch (Exception ex) {
            return false;
        }
    }

    public void clickLogout() {
        btnLogout.click();
    }

}
