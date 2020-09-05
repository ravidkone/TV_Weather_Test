package com.tv.utility;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentTest;

//@Listeners(com.fisdom.reports.TestListener.class)
public class TestBase {

	public static BrowserUtility oBrowserUtil = new BrowserUtility();
	public static CommonUtility oCommon = new CommonUtility();
	public static Constants oConstant = new Constants();
	public static String sHost = null;
	public static String sClassNameForScreenShot;

    public static ExtentTest extLogger;
	public static WebDriver driver;
	
	Logger log=Logger.getLogger(getClass().getSimpleName());

	@BeforeClass
	@Parameters("browser")
	public void triggerDependency(String browser) throws Exception {
		log.info("Test running on browser: "+browser);
		
		oCommon.loadConfigProperty(System.getProperty("user.dir")+"/src/main/java/com/tv/properties/config.properties");
		oCommon.loadLog4jProperty(System.getProperty("user.dir")+"/src/main/java/com/tv/properties/log4j.properties");

		if (System.getProperty("AutomationRunning").equalsIgnoreCase(Constants.AutomationWeb)) {
		//	oBrowserUtil.launchBrowser(System.getProperty("browser"));
			oBrowserUtil.launchBrowser(browser);

			log.info("Automation running on: "+System.getProperty("AutomationRunning"));
			log.info("Environment is: "+System.getProperty("Environment"));
	
		}
		if (System.getProperty("AutomationRunning").equalsIgnoreCase("API")) {
			sHost = System.getProperty("stageHost");
		} 
	}
	
	@AfterClass
	public void closeBrowser() {
		if(System.getProperty("AutomationRunning").equalsIgnoreCase(Constants.AutomationWeb)) {
			driver.quit();
			log.info("Browser Closed");
	}
	}
}
