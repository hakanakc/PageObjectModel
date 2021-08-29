package pageTests;

import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.SourceType;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utility.ConfigurationReader;
import utility.DriverUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LoginTest extends BaseTests {


    @Test
    public void positiveTestLogin() {
        String url = ConfigurationReader.get("url");
        String password = ConfigurationReader.get("password");

        driver.get(url);
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.getLoginPage();
        loginPage.setUsername(ConfigurationReader.get("username"));
        loginPage.setPassword(password);
        loginPage.clickLoginButton();

        loginPage.login(ConfigurationReader.get("username"), "SuperSecretPassword!");

    }

    @Test(dataProvider = "negativeUsernameData")
    public void negativeLoginTestUserName(String username, String password) {
        String url = ConfigurationReader.get("url");
        driver.get(url);
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.getLoginPage();
        loginPage.login(username, password);
        String actualError = loginPage.getErrorText();
        Assert.assertTrue(actualError.contains("Your username is invalid"));
    }
    @DataProvider
    public Object[][] negativeUsernameData() {
        String password = "SuperSecretPassword!";
        Object[][] testData = {
                {" ", password},  // empty username
                {"tomsmit", password},   // username with missing character
                {"tomsmithh", password},    // username with additional character
                {"tomsmith tomsmith", password}
        };

        return testData;
    }

    @Test(dataProvider = "negativePasswordData")
    public void negativeLoginTestPassword(String username, String password) {
        String url = ConfigurationReader.get("url");
        driver.get(url);
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.getLoginPage();
        loginPage.login(username, password);
        String actualError = loginPage.getErrorText();
        Assert.assertTrue(actualError.contains("Your password is invalid"));
    }




    @DataProvider
    public Object[][] negativePasswordData() {
        String userName = "tomsmith";
        Object[][] testData = {

                {userName, " "},  // empty password
                {userName,"SuperSecretPassword"},   // username with missing character
                {userName,"SuperSecretPassword!!"},    // username with additional character
                {userName, "randomPassword"}
        };

        return testData;




    }

    @Test
            (dataProvider = "excelDataProvider")
    public void negativeLoginTestAll(String username, String password) {
        String url = ConfigurationReader.get("url");
        driver.get(url);
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.getLoginPage();
        loginPage.login(username, password);
        String actualError = loginPage.getErrorText();
        Assert.assertTrue(actualError.contains("invalid"));
    }


    @DataProvider
//    @Test
    public Object[][] excelDataProvider(){
        Workbook wb=null;
        try {
            FileInputStream excell=new FileInputStream("resources/Book1.xlsx");
            wb= WorkbookFactory.create(excell);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sheet ws=wb.getSheet("username");// find the page username in Datalogin xls, located before
        Row row=ws.getRow(0);
        int rowNo=ws.getLastRowNum(); // find the last filled row
        rowNo+=1; // we have added one more line since cannot read the latest one.
        System.out.println("rowNo = " + rowNo);
        int columnNo=row.getLastCellNum();
        Object[][] data=new Object[rowNo][2];
        // for loop created to get each cell one by one.
        for(int i=0;i < rowNo;i++){
            for(int j=0;j<2;j++){
                // if there is a null cell we use below. otherwise will throw error.
                Cell cell=ws.getRow(i).getCell(j);
                if(cell!=null){Object cellData=cell.toString();
                    data[i][j]=cellData;
//                System.out.println(cellData);
                }
            }
        }


        return data;
    }


}