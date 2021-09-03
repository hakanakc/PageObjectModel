package pageTests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import utility.ConfigurationReader;
import utility.DriverUtil;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"testReports1/testResult.html");
     report.attachReporter(htmlReporter);
     htmlReporter.config().setReportName("Test1");
     report.setSystemInfo("Environment","Test");
     report.setSystemInfo("QA Tester","Hakan");
     report.setSystemInfo("Browser", ConfigurationReader.get("browser"));

}
@BeforeMethod

    public void beforeMethod(){

    driver= DriverUtil.get();
 //   driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    wait = new WebDriverWait(driver, 10);

}
@AfterMethod
public void AfterMethod(ITestResult result) throws IOException {
    if (result.getStatus() == ITestResult.FAILURE) {
        test.fail(result.getName());
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) DriverUtil.get();
        File file = ts.getScreenshotAs(OutputType.FILE);
        String target = System.getProperty("user.dir") + "/restReports1/" + result.getName() + date + ".jpeg";
        File finalDestination = new File(target);

        FileUtils.copyFile(file, finalDestination);
        test.addScreenCaptureFromPath(result.getName() + date + ".jpeg");
        test.fail(result.getThrowable());

    }
}

   @AfterTest
    public void tearDown() throws InterruptedException {
    Thread.sleep(3000);
report.flush();
  driver.quit();

}
// @AfterTest
//    public void afterTest(){
//
//    // Closing the report.flush();

//}



}
