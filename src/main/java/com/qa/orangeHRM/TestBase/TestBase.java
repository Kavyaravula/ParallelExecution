package com.qa.orangeHRM.TestBase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class TestBase {

	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();
	public static Properties prop;

	public TestBase() {
		prop = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("C:\\Users\\Chintu\\workspace\\OrangeHRM2\\src"
					+ "\\main\\java\\com\\qa\\orangeHRM\\Config\\config.properties");
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		try {
			prop.load(fis);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public static void selectBrowserAndOpenURL(String browserName) {
		// browserName= prop.getProperty("browsername");

		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "F:\\Selenium Drivers\\chromedriver4.exe");
			// driver= new ChromeDriver();
			tldriver.set(new ChromeDriver());

		} else if (browserName.equalsIgnoreCase("ff")) {
			System.setProperty("webdriver.gecko.driver", "F:\\Selenium Drivers\\geckodriver(1).exe");
			// driver= new FirefoxDriver();
			tldriver.set(new FirefoxDriver());
		}

		else if (browserName.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.ie.driver",
					"F:\\Selenium Drivers\\IEDriverServer_x64_3.5.1\\IEDriverServer.exe");
			// driver= new InternetExplorerDriver();
			tldriver.set(new InternetExplorerDriver());
		}

		else if (browserName.equalsIgnoreCase("headless")) {
			System.setProperty("webdriver.chrome.driver", "F:\\Selenium Drivers\\chromedriver4.exe");
			ChromeOptions chromeopt = new ChromeOptions();
			chromeopt.addArguments("--headless");
			// driver = new ChromeDriver(chromeopt);
			tldriver.set(new ChromeDriver(chromeopt));
		}

	}

	public static WebDriver getDriver() {
		return tldriver.get();

	}

}
