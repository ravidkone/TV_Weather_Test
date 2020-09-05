package com.tv.pom.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tv.utility.TestBase;

public class NdtvWeatherPage extends TestBase{
	
	@FindBy(id = "searchBox")
	WebElement searchTab;
	@FindBy(id = "Kanpur")
	WebElement selectCity;
	@FindBy(xpath = "//div[text()='Kanpur']")
	WebElement clickCityWeather;
	@FindBy(xpath = "//span[@class='heading']//b[contains(text(),'Degrees')]")
	WebElement getWeatherDetails;
	
	Logger log = Logger.getLogger(getClass().getSimpleName());
	
	public NdtvWeatherPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getWeatherPageTitle() throws InterruptedException {
		oBrowserUtil.waitForElementVisible(driver, searchTab, 5);
		return driver.getTitle();
	}
	public void enterCity() {
		searchTab.click();
		searchTab.sendKeys(System.getProperty("city"));
		selectCity.click();
	}
	public String getWeather() throws InterruptedException {
		clickCityWeather.click();
		oBrowserUtil.waitForElementVisible(driver, getWeatherDetails, 10);
		return getWeatherDetails.getText();
	}
}
