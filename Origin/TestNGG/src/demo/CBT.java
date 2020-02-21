package demo;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CBT {
	
	WebDriver driver;
	@Parameters("browser")
	@BeforeTest
	public void openBrowser(String browser) {
		if(browser.equals("Chrome")) {
			driver = new ChromeDriver();
		}
		else if(browser.equals("IE")) {
			driver = new InternetExplorerDriver();
		}
		else if(browser.equals("Edge")) {
			driver = new EdgeDriver();
		}
		else if(browser.equals("Firefox")) {
			driver = new FirefoxDriver();
		}
			
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.monster.ca/");
		
	}
	@Test(priority = 0)
	public void verifyPageTitle() {
		String expectedTitle = "Find Jobs. Build a Better Career. Find Your Calling | Monster.ca | Monster.ca";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
		
	}
	@Test(priority = 1, enabled = false)
	public void verifyText() {
		String expectedText = "Don't search for jobs.\n" + 
				"Find the right fit instead.";
		String actualText = driver.findElement(By.cssSelector("#ctl00_wacCenterStage_ccAjaxCMSPageBody_BodyContainer > div > main > section.hero-section > div > div > div > h1.section-title.hidden-xs")).getText();
		Assert.assertEquals(actualText, expectedText);
	
	}
	@Test(priority = 3)
	public void VerifyLoginBtnVisibility() {
		boolean loginBtnVisible = driver.findElement(By.cssSelector("#mobile-navbar-search > ul > li.login-hide.signInLabel > a")).isDisplayed();
		Assert.assertTrue(loginBtnVisible);
	
	}
	@Test(priority = 2)
	public void verifySearchBtnIsEnabled() {
		boolean SearchBtnEnabled = driver.findElement(By.cssSelector("#doQuickSearch2")).isEnabled();
		Assert.assertTrue(SearchBtnEnabled);
		
	}
	@AfterTest
	public void closeBrowser() {
		driver.close();
	}
}
