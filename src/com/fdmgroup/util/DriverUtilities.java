package com.fdmgroup.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverUtilities {
	private static DriverUtilities instance;
	private WebDriver driver;
	
	private DriverUtilities() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static DriverUtilities getInstance(){
		if(instance == null){
			instance = new DriverUtilities();
		}
		
		return instance;
	}
	
	public WebDriver getDriver(){
		if(driver == null){
			createDriver();
		}
		return driver;
	}

	private void createDriver() {
		String driverName = getDriverName();
		
		switch (driverName) {
		case "Google Chrome":
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			this.driver = new ChromeDriver();
			break;
		case "Firefox":
			System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
			this.driver = new FirefoxDriver();
			break;
		default:
			break;
		}
		
	}

	private String getDriverName() {
		Properties config = new Properties();
		String driverName = "";
		
		try {
			config.load(new FileInputStream("config.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(String key : config.stringPropertyNames()){
			if(key.equals("Browser")){
				driverName = config.getProperty(key);
			}
		}
		
		return driverName;
	}
}
