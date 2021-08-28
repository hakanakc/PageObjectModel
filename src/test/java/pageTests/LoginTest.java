package pageTests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utility.ConfigurationReader;
import utility.DriverUtil;

public class LoginTest extends BaseTests {



    @Test
    public void positiveTestLogin(){
  String url = ConfigurationReader.get("url");
        String password = ConfigurationReader.get("password");

        driver.get(url);
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.getLoginPage();
loginPage.setUsername(ConfigurationReader.get("username"));
 loginPage.setPassword(password);
 loginPage.clickLoginButton();

loginPage.login(ConfigurationReader.get("username"), "123456");

    }
    public void negativeLoginTest(){

        String url = ConfigurationReader.get("url");


    }


}
