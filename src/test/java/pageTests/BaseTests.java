package pageTests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
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

public ExtentReports report;
public ExtentHtmlReporter htmlReporter;
public ExtentTest test;



@BeforeTest
    public void beforeTest(){

    //Reports setup

    report = new ExtentReports();
    htmlReporter=

}
@BeforeMethod

    public void beforeMethod(){

    driver= DriverUtil.get();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    wait = new WebDriverWait(driver, 10);

}

@AfterTest
    public void tearDown() throws InterruptedException {
    Thread.sleep(3000);

  driver.quit();

}
// @AfterTest
//    public void afterTest(){
//
//    // Closing the report.flush();

//}



}
