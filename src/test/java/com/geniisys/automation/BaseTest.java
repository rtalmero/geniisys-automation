package com.geniisys.automation;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.geniisys.automation.common.BrowserDriver;
import com.geniisys.automation.main.pages.LogInPage;

public abstract class BaseTest {

	protected BrowserDriver driver;
	private final String URL = "http://192.10.10.240:9090/Geniisys/";
	private final String USERNAME = "CPI";
	private final String PASSWORD = "CPI12345!";

	@BeforeTest
	public void setUp() {
		setDriver(new BrowserDriver("FIREFOX"));
		driver.manage().window().maximize();
		driver.get(URL);

		LogInPage loginPage = new LogInPage(driver);
		loginPage.logInAs(USERNAME, PASSWORD);
	}

	@AfterTest
	public void breakDown() {
		if (null != driver) {
			driver.quit();
		}
	}

	public BrowserDriver getDriver() {
		return driver;
	}

	public void setDriver(BrowserDriver driver) {
		this.driver = driver;
	}
	
}
