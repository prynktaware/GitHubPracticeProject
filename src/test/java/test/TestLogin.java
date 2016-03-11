package test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import errorcollectors.ErrorCollector;

public class TestLogin {

	public static WebDriver driver;
	
	@BeforeSuite
	public void doSetup(){
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
		driver.navigate().to("http://gmail.com");
		
	}
	
  @Test
  public void testDoLogin() throws IOException {
	  driver.findElement(By.xpath("//*[@id='Email']")).sendKeys(Keys.chord(Keys.CONTROL,"a"),"prynktaware@gmail.com");
	  driver.findElement(By.xpath("//*[@id='next']")).click();
	  driver.findElement(By.xpath("//*[@id='Passwd']")).sendKeys(Keys.chord(Keys.CONTROL,"a"),"smartmove23");
	  driver.findElement(By.xpath("//*[@id='signIn']")).click();
	  try{
		  String actual=driver.findElement(By.xpath("//*[@id='gb']/div[1]/div[1]/div[1]/div/span")).getText();
		  String expected= "Priyanka";
		  Assert.assertEquals(actual, expected);
	  }catch(Exception e)
  {
	  System.out.println("Error Encountered");
	  ErrorCollector.addVerificationFailure(e);
  }
	 
	  File srcFile= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	  File destFile= new File("C:\\Users\\James\\Documents\\Pinky\\Selenium_Webriver\\Screenshot\\doLoginResult.jpeg");
	  FileUtils.copyFile(srcFile, destFile);
	  
}
  
  @AfterSuite
  public void tearDown(){
	  driver.quit();
  }
}
