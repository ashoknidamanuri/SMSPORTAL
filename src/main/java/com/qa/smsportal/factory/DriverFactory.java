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
	
	public WebDriver initDriver(Properties prop) {
		
		optionsManager = new OptionsManager(prop);
		
		String browserName = prop.getProperty("browser").toLowerCase().trim();

		System.out.println("browser name is "+ " "+ browserName);
		
		if(browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver(optionsManager.getChromeOptions());
					
		}
		
		else if(browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
					
		}
		
		else if(browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver(optionsManager.getEdgeOptions());
					
		}
		
		else {
			System.out.println("Please pass the correct browser name" + " "+ browserName);
		}
		
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();

		driver.get(prop.getProperty("url"));
		return driver;	
		
	}
	
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
