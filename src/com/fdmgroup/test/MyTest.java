package com.fdmgroup.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import com.fdmgroup.util.DriverUtilities;

public class MyTest {

	DriverUtilities myDU;
	WebDriver driver;
	
	@Before
	public void init(){
		myDU = DriverUtilities.getInstance();
		driver = myDU.getDriver();
	}
	
	@Test
	@Ignore
	public void functionalityTest(){
		//driver.get("http://unxbtn001/TradingPlatform_CLEAN/");
		driver.get("http://www.bbc.co.uk");
		driver.manage().window().maximize();
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());
		driver.navigate().refresh();
		driver.get("http://www.google.com");
		driver.navigate().back();
		driver.navigate().forward();
		
	}
	
	@Test
	@Ignore
	public void module9s26(){
		driver.get("http://unxbtn001/TradingPlatform_CLEAN/");
		driver.findElement(By.name("j_username")).sendKeys("m.murdock@test.com");
		driver.findElement(By.partialLinkText("Regis")).click();
		driver.findElement(By.cssSelector("img[alt='logo']")).click();
		driver.findElement(By.partialLinkText("Regis")).click();
		driver.findElement(By.xpath("//*[@id='main']/p[2]/a")).click();
		driver.findElement(By.xpath("//*[@id='login']//tr[3]/td/input")).click();
	}
	
	@Test
	@Ignore
	public void module10s15(){
		driver.get("http://unxbtn001/TradingPlatform_CLEAN/");
		driver.findElement(By.name("j_username")).sendKeys("srogers");
		driver.findElement(By.name("j_password")).sendKeys("Pass123");
		driver.findElement(By.name("submit")).click();
		driver.findElement(By.partialLinkText("Buy Stock")).click();
		driver.findElement(By.linkText("S")).click();
		System.out.println(driver.findElement(By.xpath("//*[@id='SSE.L']/td[3]")).getText());
		
		//slide 19
		
		if(driver.findElement(By.partialLinkText("logout")).isEnabled()){
			System.out.println("Logout link exists.");
			
			System.out.println(driver.findElement(By.id("submitBtn")).isEnabled());
			driver.findElement(By.cssSelector("input[value='SSE.L']")).click();
			if(driver.findElement(By.cssSelector("input[value='SSE.L']")).isSelected())
				System.out.println("Button is selected.");
			System.out.println(driver.findElement(By.id("submitBtn")).isEnabled());
		}
		
		else
			System.out.println("Logout does not exist.");
	}
	
	@Test
	@Ignore
	public void TP_Bal_03(){
		driver.get("http://unxbtn001/TradingPlatform_CLEAN/");
		driver.findElement(By.name("j_username")).sendKeys("Spiderman");
		driver.findElement(By.name("j_password")).sendKeys("spoder");
		driver.findElement(By.name("submit")).click();
		driver.findElement(By.partialLinkText("Check Balance")).click();
		driver.findElement(By.cssSelector("a[href='addaccount.html']")).click();
		
		Select select = new Select(driver.findElement(By.name("currency")));
		select.selectByValue("USD");
		driver.findElement(By.xpath("//*[@id='command']//td[3]/input")).click();
		
		driver.findElement(By.cssSelector("input[value='USD']")).findElement(By.xpath("..")).click();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.name("credit")).sendKeys("50000");
		driver.findElement(By.id("submitBtn")).click();
		
		String value = driver.findElement(By.xpath("//td[contains(text(), 'USD')]")).
			findElement(By.xpath("..")).findElement(By.xpath(".//td[3]")).getText();
		
		
		Assert.assertEquals("50,000.00", value);
	}
	
	@Test
	public void TP_Buy_01(){
		driver.get("http://unxbtn001/TradingPlatform_CLEAN/");
		driver.findElement(By.name("j_username")).sendKeys("Spiderman");
		driver.findElement(By.name("j_password")).sendKeys("spoder");
		driver.findElement(By.name("submit")).click();
		driver.findElement(By.partialLinkText("Buy Stock")).click();
		
		driver.findElement(By.linkText("G")).click();
		driver.findElement(By.xpath("//*[@id='GLEN.L']/td[4]/input")).click();
		
		Assert.assertEquals(true, driver.findElement(By.xpath("//*[@id='GLEN.L']/td[4]/input")).isSelected());
		
		driver.findElement(By.id("submitBtn")).click();
		driver.findElement(By.name("amount")).sendKeys("100");
		driver.findElement(By.id("calcBtn")).click();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.cssSelector("input[value='Confirm']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='transaction_summary']//tr[3]/td[2]")).getText(), "100");
		driver.findElement(By.xpath("//*[@id='tabs25']/ul/li[6]/a")).click();
	}
	
	
	@After
	public void destroy(){
		try {
			//Prevent browser from closing immediately after finishing test
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.quit();
	}
	
}
