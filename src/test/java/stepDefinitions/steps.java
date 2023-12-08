package stepDefinitions;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataReader;

public class steps {
    WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    MyAccountPage myAccountPage;

    List<HashMap<String, String>> dataMap; //Data driven

    public Logger logger; //for logging
    public ResourceBundle resourceBundle; // for reading properties file
    public String browser; //to store browser name


    @Before
    public void setup()    //Junit hook - executes once before starting
    {
        //for logging
        logger = LogManager.getLogger(this.getClass());
        //Reading config.properties (for browser)
        resourceBundle = ResourceBundle.getBundle("config");
        browser = resourceBundle.getString("browser");

        myAccountPage = new MyAccountPage(driver);


    }

    @After
    public void tearDown(Scenario scenario) {
        System.out.println("Scenario status ======>" + scenario.getStatus());
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        driver.quit();
    }

    @Given("user launch browser")
    public void user_launch_browser() {
        if (browser.equals("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equals("edge")) {
            driver = new EdgeDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Given("opens URL {string}")
    public void opens_url(String url) {
        driver.get(url);
        driver.manage().window().maximize();
    }

    @When("user navigate to myAccount menu")
    public void user_navigate_to_my_account() {
        homePage = new HomePage(driver);
        homePage.clickMyAccount();
        logger.info("Clicked on My Account ");

    }

    @When("click on Login")
    public void click_on_login() {
        homePage.clickLogin();
        logger.info("Clicked on Login ");
    }

    @When("user enters email as {string} and password as {string}")
    public void user_enters_email_as_and_password_as(String email, String pwd) {
        loginPage = new LoginPage(driver);

        loginPage.setEmail(email);
        logger.info("Provided Email ");
        loginPage.setPassword(pwd);
        logger.info("Provided Password ");
    }

    @When("click on Login button")
    public void click_on_login_button() {
        loginPage.clickLogin();
        logger.info("Clicked on Login button");
    }


    @Then("user navigates to myAccount page")
    public void user_navigates_to_my_account_page() {

        MyAccountPage page = new MyAccountPage(driver);

        boolean targetpage = page.isMyAccountPageExists();

        if (targetpage) {
            logger.info("Login Success ");
            Assert.assertTrue(true);
        } else {
            logger.error("Login Failed ");
            Assert.fail();
        }
    }

    //*******   Data Driven test method    **************
    @Then("check user navigates to myAccount Page by passing email and password with excel row {string}")
    public void check_user_navigates_to_my_account_page_by_passing_email_and_password_with_excel_data(String rows) {
        dataMap = DataReader.data(System.getProperty("user.dir") + "\\testData\\LoginData.xlsx", "Sheet1");

        int index = Integer.parseInt(rows) - 1;
        String email = (dataMap.get(index).get("username")).trim();
        String pwd = dataMap.get(index).get("password").trim();
        String exp = dataMap.get(index).get("res").trim();

        loginPage = new LoginPage(driver);
        loginPage.setEmail(email);
        loginPage.setPassword(pwd);
        loginPage.clickLogin();

        MyAccountPage myAccountPage = new MyAccountPage(driver);
        boolean targetPage = myAccountPage.isMyAccountPageExists();

        try {
            if (exp.equals("valid")) {
                if (targetPage) {
                    myAccountPage.clickLogout();
                    Assert.assertTrue(true);
                } else {
                    Assert.fail();
                }
            }
            if (exp.equals("invalid")) {
                if (targetPage) {
                    Assert.fail();
                } else {
                    Assert.assertTrue(true);
                }
            }
        } catch (Exception e) {
            Assert.fail();
        }
        driver.close();
    }

    //*******   Account Registration Methods    **************


}
