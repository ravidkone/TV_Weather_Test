package com.tv.utility;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserUtility extends TestBase {

	Logger log = Logger.getLogger(getClass().getSimpleName());

	public void launchBrowser(String browser) throws Exception {

		if (browser.equalsIgnoreCase("chrome")) {
			log.info("Launching Browser: " + browser);
			//Chrome options class is used to manipulate various properties of Chrome driver.	
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions=new ChromeOptions();
			chromeOptions.addArguments("--disable-notifications");
			
		// Create Chrome instance
			driver=new ChromeDriver(chromeOptions);

		} else if (browser.equalsIgnoreCase("firefox")) {
			log.info("Launching Browser: " + browser);
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}else {
			// If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}

		driver.get(System.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	public boolean isDisplayed(WebElement ele) {
		boolean bRes_flag = false;
		try {
			if (ele.isDisplayed()) {
				log.info("Displayed " + ele);
				bRes_flag = true;
			}
		} catch (Exception e) {
		}

		return bRes_flag;
	}

	public boolean waitForElementVisible(WebDriver driver, WebElement ele, int iTimeInSeconds)
			throws InterruptedException {
		boolean bRes_flag = false;
		for (int i = 0; i < iTimeInSeconds; i++) {
			if (!isDisplayed(ele))
				Thread.sleep(1000);
			else
				break;
		}

		return bRes_flag;

	}

}
