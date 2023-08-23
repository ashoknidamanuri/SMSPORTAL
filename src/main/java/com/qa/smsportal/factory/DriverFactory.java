package com.qa.smsportal.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;
	public static String highlight;
	public static ThreadLocal<WebDriver> tlDriver =new ThreadLocal<WebDriver>();
	/**
	 * this method is initializing the driver on the basis of given browser name
	 * 
	 * @param browserName
	 * @return this returns the driver
	 */
	public WebDriver initDriver(Properties prop) {
		
		optionsManager = new OptionsManager(prop);
		
		highlight = prop.getProperty("highlight").trim();

		String browserName = prop.getProperty("browser").toLowerCase().trim();
		
		System.out.println("browser name is "+ " "+ browserName);
		
		if(browserName.equalsIgnoreCase("chrome")) {
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			
			
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			}

		
		
		else if(browserName.equalsIgnoreCase("firefox")) {
			//driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
					
		}
		
		else if(browserName.equalsIgnoreCase("edge")) {
			//driver = new EdgeDriver(optionsManager.getEdgeOptions());
					tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
		}
		
		else {
			System.out.println("Please pass the correct browser name" + " "+ browserName);
		}
		
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url").trim());
		return getDriver();
		
	}
	// get local thread copy of the driver
	public synchronized static WebDriver getDriver() {
		return tlDriver.get();
	}

	   // this method reading of properties form the .config properties file.
	
	public Properties initProp() {
		prop = new Properties();
		try {
			FileInputStream ip= new FileInputStream("./src\\main\\resources\\config\\config.properties");
				prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	
}
