package com.tv.pom.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
		oBrowserUtil.waitForElementVisible(driver, notificationPopUp, 5);
		if(oBrowserUtil.isDisplayed(notificationPopUp)) {
			notificationPopUp.click();
		}else {
			System.out.println("Pop not availabnle");
		}
	}
	public void clickOnWeatherTab() throws InterruptedException {
		oBrowserUtil.waitForElementVisible(driver, subMenu, 5);
		subMenu.click();
		oBrowserUtil.waitForElementVisible(driver, weatherTab, 5);
		weatherTab.click();
	}
}
