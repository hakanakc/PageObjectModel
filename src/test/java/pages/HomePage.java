package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.DriverUtil;

public class HomePage {


@FindBy(linkText = "Form Authentication")
    WebElement loginPageLink;

 @FindBy(linkText = "Frames")
    WebElement framesLink;

    public HomePage() {

        PageFactory.initElements(DriverUtil.get(),this);
    }



    public   LoginPage getLoginPage() {

        loginPageLink.click();
     return new LoginPage();
    }
}
