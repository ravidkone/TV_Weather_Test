package com.tv.testcases;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.tv.utility.Constants;
import com.tv.pom.pages.NdtvHomePage;
import com.tv.pom.pages.NdtvWeatherPage;
import com.tv.utility.TestBase;

public class WeatherTest extends TestBase{

	NdtvHomePage homePage;
	NdtvWeatherPage weatherPage;
	public double UI_Temp;
	Logger log = Logger.getLogger(getClass().getSimpleName());

	@BeforeMethod
	public void setUp() {
		homePage = new NdtvHomePage(driver);
		weatherPage=new NdtvWeatherPage(driver);
		sClassNameForScreenShot = getClass().getSimpleName();
	}
	
	@Test(priority = 1)
	public void verifyHomePageTitle() {
		System.out.println("Title is: " + homePage.getHomePageTitle());
		Assert.assertEquals(Constants.HomePageTitle, homePage.getHomePageTitle());
	}
	
	@Test(priority = 2)
	public void clickNotificationPopUp() throws InterruptedException {
		homePage.clickPopUp();
	}
	@Test(priority = 3)
	public void clickonWeatherTab() throws InterruptedException {
		homePage.clickOnWeatherTab();
	}
	@Test(priority = 4)
	public void verifyWeatherPageTitle() throws InterruptedException {
		Assert.assertEquals(Constants.WeatherPageTitle, weatherPage.getWeatherPageTitle());
		System.out.println("Weather Page Title is: " + weatherPage.getWeatherPageTitle());
	}
	@Test(priority = 5)
	public void selectCity() throws InterruptedException {
		weatherPage.enterCity();
		String temprature=weatherPage.getWeather();
		String sTemp=temprature.replaceAll("\\D", "");
		UI_Temp=Double.parseDouble(sTemp);
		System.out.println("UI temp in degree: "+UI_Temp);
	}
	
}
