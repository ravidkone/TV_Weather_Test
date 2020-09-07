package com.tv.testcases;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tv.pom.pages.NdtvHomePage;
import com.tv.pom.pages.NdtvWeatherPage;
import com.tv.utility.Constants;
import com.tv.utility.TestBase;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CompareTemp extends TestBase{
	

	NdtvHomePage homePage;
	NdtvWeatherPage weatherPage;
	public double uiTemp;
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
			uiTemp=Double.parseDouble(sTemp);
			System.out.println("temp is: "+uiTemp);
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
		System.out.println("API Temp in degree: "+API_Temp);
	}
	
	@Test(priority = 3)
	public void compareTemprature() {
		String res = oCommon.compare(uiTemp, API_Temp);
		System.out.println("result is:" + res);
	}


}
