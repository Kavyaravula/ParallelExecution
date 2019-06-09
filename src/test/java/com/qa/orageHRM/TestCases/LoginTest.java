package com.qa.orageHRM.TestCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.orangeHRM.Pages.HomePage;
import com.qa.orangeHRM.Pages.LoginPage;
import com.qa.orangeHRM.TestBase.TestBase;

public class LoginTest extends TestBase {
	public  WebDriver driver;
	public LoginPage loginpage;
	public HomePage homepage;
	public LoginTest(){
		super();
	}
	
	@BeforeMethod (groups={"Regression","Smoke"})
	@Parameters({"browser"})
	public void setUp(String strBrowser){
		TestBase.selectBrowserAndOpenURL(strBrowser);
		driver=TestBase.getDriver();
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		loginpage= new LoginPage();
	}
	
	@Test(priority=1,enabled=true, groups={"Regression"})
	
	public void verifyLogoTest(){
		
		boolean flag=loginpage.verifyOrangeHRMLogo();
		Assert.assertTrue(flag);	
		
	}

	@Test(priority=2, groups={"Regression","Smoke"})
	public void verifyLoginTest(){
		homepage=loginpage.verifyLogin(prop.getProperty("username"), prop.getProperty("password"));
		boolean falg=homepage.userInfodeatails.isDisplayed();
		Assert.assertTrue(falg);
	}
	
	@AfterMethod (groups={"Regression","Smoke"})
	
	public void tearDown(){
		driver.quit();
	}
}
