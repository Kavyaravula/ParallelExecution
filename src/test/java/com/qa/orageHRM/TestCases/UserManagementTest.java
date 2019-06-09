package com.qa.orageHRM.TestCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.orangeHRM.Pages.HomePage;
import com.qa.orangeHRM.Pages.LoginPage;
import com.qa.orangeHRM.Pages.UserManagementPage;
import com.qa.orangeHRM.TestBase.TestBase;
import com.qa.orangeHRM.TestUtil.TestUtil;

public class UserManagementTest extends TestBase {
	public WebDriver driver;
	public LoginPage loginpage;
	public HomePage homepage;
	public UserManagementPage umpage;
	public TestUtil testutil;
	
	public UserManagementTest(){
		super();
	}
	
	@BeforeMethod
	@Parameters({"browser"})
	public void setUp(String nameOfBrowser){
		TestBase.selectBrowserAndOpenURL(nameOfBrowser);
		driver= TestBase.getDriver();
		driver.get(prop.getProperty("url"));
		//driver.manage().deleteAllCookies();
	
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		loginpage= new LoginPage();
		homepage=loginpage.verifyLogin(prop.getProperty("username"), prop.getProperty("password"));
		umpage= new UserManagementPage();
		umpage.naviagteToAdminTab();
		
	}
	
	@Test(priority=1)
	public void verifyNavigationToAddUserScreen(){
		String AdduserPageHeading=umpage.naviagteToAddUserScreen();
		Assert.assertEquals(AdduserPageHeading, "Add User");
	}
	
	
	@DataProvider(name="UserDetails", parallel= true )
	public Object[][] getDataFromExcel(){
		testutil= new TestUtil();
		return testutil.readExcelDate("C:\\Users\\Chintu\\workspace\\OrangeHRM\\src\\main\\java\\com\\qa\\orangeHRM\\TestData\\AddUsers.xlsx");
	}
	
	@Test(priority=2, dataProvider="UserDetails")
	public void addUsers(String empNAME, String userNAME, String strPassword, String strConfirmPassword ){
		umpage.naviagteToAddUserScreen();
		umpage.enterUserDeatilsAndSave(empNAME, userNAME, strPassword, strConfirmPassword);
		
	}

	
	@AfterMethod
	public void tearDown3(){
		driver.quit();
	}
}
