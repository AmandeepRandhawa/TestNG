package demo;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ScreenshotExp {
	WebDriver driver;

	@BeforeTest 
	public void openBrowser() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://www.newtours.demoaut.com/");
	}


	@Test
	public void ticketbooking() throws IOException {
		try {
			driver.findElement(By.name("userName")).sendKeys("mercury");
			driver.findElement(By.name("password")).sendKeys("mercury");
			driver.findElement(By.name("login")).click();

			driver.findElement(By.name("findFlights")).click();
			driver.findElement(By.name("reserveFlights")).click();
			driver.findElement(By.name("passFirst0")).sendKeys("John");
			driver.findElement(By.name("passLast0")).sendKeys("Smith");
			driver.findElement(By.name("creditnumber")).sendKeys("98787679");
			driver.findElement(By.name("buyFlights")).click();

			String expectedMsg = "Your itinerary has been booked!";
			String actualMsg = driver.findElement(By.cssSelector("body > div > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(4) > td > table > tbody > tr:nth-child(1) > td:nth-child(2) > table > tbody > tr:nth-child(3) > td > p > font > b > font:nth-child(2)")).getText();

			Assert.assertEquals(actualMsg, expectedMsg);
		}
		catch(Exception ex) {
			takeScreenshot();
			Assert.fail("Script failed due to exception: "+ex);
		}
			
		
	}


	private void takeScreenshot() throws IOException {
		File ss = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(ss, new File("Macintosh HD⁩ ▸ ⁨Users⁩ ▸ ⁨randhawa⁩"+(new Random().nextInt())+".jpg"));
	}

	@AfterTest
	public void closeApplication() {
		driver.close();
	}
}




