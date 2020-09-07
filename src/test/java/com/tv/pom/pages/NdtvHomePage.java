package com.tv.pom.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;
import com.tv.utility.TestBase;

public class NdtvHomePage extends TestBase{
	
	@FindBy(xpath = "//a[text()='No Thanks']")
	WebElement notificationPopUp;
	@FindBy(id = "h_sub_menu")
	WebElement subMenu;
	@FindBy(xpath = "//a[text()='WEATHER']")
	WebElement weatherTab;
	
	Logger log = Logger.getLogger(getClass().getSimpleName());
	
	public NdtvHomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getHomePageTitle() {
		return driver.getTitle();
	}
	
	public void clickPopUp() throws InterruptedException {
		oBrowserUtil.waitForElementVisible(driver, notificationPopUp, 2);
		if(oBrowserUtil.isDisplayed(notificationPopUp)) {
			notificationPopUp.click();
		}else {
			System.out.println("Pop not availabnle");
		}
	}
	public void clickOnWeatherTab() throws Exception {
		//oBrowserUtil.waitForElementVisible(driver, subMenu, 2);
		if(oBrowserUtil.isDisplayed(subMenu)) {
		subMenu.click();
		extLogger.log(Status.INFO,"Clicked on sub Menu");
		}else {
			throw new Exception("Unable to find sub menu button ");
		}
		
		oBrowserUtil.waitForElementVisible(driver, weatherTab, 2);
		if(oBrowserUtil.isDisplayed(weatherTab)) {
		weatherTab.click();
		extLogger.log(Status.INFO,"Clicked on Weather Tab");
		}else {
			throw new Exception("Unable to find Weather Tab");
		}
	}
}
