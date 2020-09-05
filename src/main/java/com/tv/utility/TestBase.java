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
	public static RestUtility oRestUtil=new RestUtility();
	public static String sHost = null;
	public static String sClassNameForScreenShot;

    public static ExtentTest extLogger;
	public static WebDriver driver;
	
	Logger log=Logger.getLogger(getClass().getSimpleName());

	@BeforeClass
	@Parameters("Automation")
	public void triggerDependency(String automation) throws Exception {
		log.info("Test running on: "+automation);
		
		oCommon.loadConfigProperty(System.getProperty("user.dir")+"/src/main/java/com/tv/properties/config.properties");
		oCommon.loadLog4jProperty(System.getProperty("user.dir")+"/src/main/java/com/tv/properties/log4j.properties");

		if (Constants.AutomationWeb.equalsIgnoreCase(automation)) {
			oBrowserUtil.launchBrowser("chrome");

			log.info("Automation running on: "+automation);
			log.info("Environment is: "+System.getProperty("Environment"));
	
		}
		if (Constants.AutomationAPI.equalsIgnoreCase(automation)) {
			log.info("Automation running on: "+automation);
			log.info("Environment is: "+System.getProperty("Environment"));
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
