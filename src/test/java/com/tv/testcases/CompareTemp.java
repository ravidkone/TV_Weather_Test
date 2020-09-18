package com.tv.testcases;

import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.tv.pom.pages.NdtvHomePage;
import com.tv.pom.pages.NdtvWeatherPage;
import com.tv.utility.Constants;
import com.tv.utility.TestBase;
import com.xpand.annotations.Xray;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CompareTemp extends TestBase{
	

	NdtvHomePage homePage;
	NdtvWeatherPage weatherPage;
	public double UI_Temp;
	String sURL;
	Response res;
	double API_Temp;
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
		
			homePage.clickOnWeatherTab();
			weatherPage.enterCity();
			String temprature=weatherPage.getWeather();
			String sTemp=temprature.replaceAll("\\D", "");
			UI_Temp=Double.parseDouble(sTemp);
			extLogger.log(Status.INFO,"Temprature from Web UI is: "+UI_Temp);
			System.out.println("Temprature from Web UI is: "+UI_Temp);
	        ITestResult result = Reporter.getCurrentTestResult();   
	        result.setAttribute("requirement", "CALC-1234");
	        result.setAttribute("test", "CRM-1");

	}
	@Test(priority = 2)
	public void getAPITemprature() throws Exception {
		sURL = oCommon.generateURL(System.getProperty("weather"));
		System.out.println("URI is: "+sURL);
		res = oRestUtil.ufGet(sURL, oCommon.getCityDetails());
		String	temprature=res.jsonPath().getString("main.temp");
		double t=273.15;
		double t1=Double.parseDouble(temprature);
		API_Temp=t1-t; 
		extLogger.log(Status.INFO,"Temprature from API is: "+API_Temp);
		System.out.println("Temprature from API is: "+API_Temp);
        ITestResult result = Reporter.getCurrentTestResult();   
        result.setAttribute("requirement", "CALC-1234");
        result.setAttribute("test", "CRM-2");

	}
	
	@Test(priority = 3)
	public void compareTemprature() {
		String res = oCommon.compare(UI_Temp, API_Temp);
		extLogger.log(Status.INFO,"Result is: "+res);
		System.out.println("Result is: " + res);
        ITestResult result = Reporter.getCurrentTestResult();  
        result.setAttribute("requirement", "CALC-1234");
        result.setAttribute("test", "CRM-3");

	}


}
