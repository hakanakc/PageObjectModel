package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.DriverUtil;

public class LoginPage {

    @FindBy(id="username")
    WebElement username;

    @FindBy(id="password")
    WebElement password;

    @FindBy(className = "radius")
    WebElement loginButton;

    @FindBy(id = "flash")
    WebElement errorMessage;

    public LoginPage() {
        PageFactory.initElements(DriverUtil.get(),this);
    }

    public void setUsername(String inputUsername){

        username.sendKeys(inputUsername);
    }

    public void setPassword(String inputPassword ){

        password.sendKeys(inputPassword);

    }
     public void clickLoginButton(){

        loginButton.click();
     }

     public void login(String username,String password){

        this.username.sendKeys(username);
        this.password.sendKeys(password);
        loginButton.click();

     }

     public String getErrorText(){

        String errorMessage= this.errorMessage.getText();

       return errorMessage;

    }


    }



