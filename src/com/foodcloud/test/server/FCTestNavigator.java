package com.foodcloud.test.server;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.appium.java_client.MobileElement;

//import io.appium.java_client.android.androiddriver;

import io.appium.java_client.android.AndroidDriver;

/**
 * move in this class all utilities (Wrapping Selenium webdriver) (wrapper class) 
 * Rename as FoodCloudServer 
 * @author Viviana
 *
 */
public class FCTestNavigator {

	private FCTestServer server;
	private static String CONFIG_FILE_PATH = "./properties/configuration.properties";
	private String webDriverType; 
	private String webDriverPath; 

//	private 
	
	public FCTestServer getServer() {
		return server;
	}

	public WebDriver getDriver() {
		return server.getDriver();
	}

	public void setup() {
		// readFile();
		String browser = "googleChrome";
//		String browser = "android";
					
		// setting webdriver
		WebDriver driver = null;
		switch (browser) {
		case "googleChrome":
			// setting properties first
			webDriverType = "webdriver.chrome.driver";
			webDriverPath = "C:/WebDriver/chromedriver_win32_2_27/chromedriver.exe";

			System.setProperty(webDriverType, webDriverPath);

			driver = new ChromeDriver();
			break;

		case "firefox":
			driver =  new FirefoxDriver();
			break;

		case "explorer":
			webDriverType = "webdriver.ie.driver";
			webDriverPath = "C:/WebDriver/iexploredriver.exe";

			System.setProperty(webDriverType, webDriverPath);

			driver = new InternetExplorerDriver();
			break;
			
		case "android":
					
			  File appDir = new File("C:/WebDriver");
		      File app = new File(appDir, "ContactManager.apk");
		      org.openqa.selenium.remote.DesiredCapabilities capabilities = new org.openqa.selenium.remote.DesiredCapabilities();
		      capabilities.setCapability("device","Android");

		      //mandatory capabilities
		      capabilities.setCapability("deviceName","Android");
		      capabilities.setCapability("platformName","Android");

		      //other caps
		      capabilities.setCapability("app", app.getAbsolutePath());
		     // driver =  new AndroidDriver(capabilities);
		      
		      try {
		    	  //127.0.0.1:4723
		    	  URL url = new URL("http://127.0.0.1:4723/wd/hub"); //http://localhost:4723");
				driver = new AndroidDriver<MobileElement>(url,capabilities);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

		default:
			break;
		}
				
		server = new FCTestServer(driver);
	}

	/**
	 * Read work in progress 
	 */
	private void readFile() {
		File file = new File(CONFIG_FILE_PATH);
		
		FileInputStream stream = null;
		try {
			stream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BufferedInputStream buffer = new BufferedInputStream(stream);
		
			try {
				int temp = buffer.read();
				
	//			String test = temp.toString();
			} catch (IOException e) {
				e.printStackTrace();
			}
				
	}
	
/*	
	C:\Program Files (x86)\Appium\node_modules\appium\node_modules
	
	File uri = "C:/Program Files (x86)/Appium/node_modules/appium/node_modules/node-idevice/main.js";

	File uri = "/usr/local/lib/node_modules/appium/build/lib/main.js";
	
	AppiumServiceBuilder builder = new AppiumServiceBuilder()
            .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
            .withArgument(GeneralServerFlag.APP, System.getProperty("user.dir") + "/build/wordpress.apk")
            .withArgument(GeneralServerFlag.LOG_LEVEL, "info")
            .withLogFile(new File(System.getProperty("user.dir") + "/target/logs/sample.txt"))
            .usingAnyFreePort()
    //;
    appiumDriverLocalService = builder.build();
    appiumDriverLocalService.start();
    
 */   

	
}
