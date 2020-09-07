package com.tv.testcases;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tv.pom.pages.NdtvHomePage;
import com.tv.pom.pages.NdtvWeatherPage;
import com.tv.utility.Constants;
import com.tv.utility.TestBase;

public class CompareTemp extends TestBase{
	

	NdtvHomePage homePage;
	NdtvWeatherPage weatherPage;
	public double uiTemp;
	Logger log = Logger.getLogger(getClass().getSimpleName());
	
//	public CompareTemp() {
//		Constants.AutomationWeb="Web";
//	}
	
	@BeforeMethod
	public void setUp() {
		homePage = new NdtvHomePage(driver);
		weatherPage=new NdtvWeatherPage(driver);
		sClassNameForScreenShot = getClass().getSimpleName();
	}
	
	@Test(priority = 1)
	public void getUITemprature() throws Exception {
		
			homePage.clickPopUp();
			homePage.clickOnWeatherTab();
			weatherPage.enterCity();
			String temprature=weatherPage.getWeather();
			String sTemp=temprature.replaceAll("\\D", "");
			uiTemp=Double.parseDouble(sTemp);
			System.out.println("temp is: "+uiTemp);
	}


}
