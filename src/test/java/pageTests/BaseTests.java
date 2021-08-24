package pageTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import utility.DriverUtil;

import java.util.concurrent.TimeUnit;

public class BaseTests {

public WebDriver driver;

public WebDriverWait wait;

@BeforeTest
    public void beforeTest(){

    //Reports setup

}
@BeforeMethod

    public void beforeMethod(){

    driver= DriverUtil.get();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    wait = new WebDriverWait(driver, 10);

}

@AfterMethod
    public void tearDown() throws InterruptedException {
    Thread.sleep(3000);

  //  Driver.closeDriver();

}
@AfterTest
    public void afterTest(){

    // Closing the report.flush();

}



}
