package com.tv.pom.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import com.tv.utility.TestBase;

public class NdtvWeatherPage extends TestBase {
	public static String city = System.getProperty("city");
	public static String defaultCity = System.getProperty("DefaultCity");
	@FindBy(id = "searchBox")
	WebElement searchTab;
	@FindBy(xpath = "//div[@class='message']/label")
	List<WebElement> Citylist;
	@FindBy(id = "Kanpur")
	WebElement selectCity;
	@FindBy(xpath = "//div[text()='Kanpur']")
	WebElement clickCityWeather;
	@FindBy(xpath = "//span[@class='heading']//b[contains(text(),'Degrees')]")
	WebElement getWeatherDetails;

	Logger log = Logger.getLogger(getClass().getSimpleName());

	public NdtvWeatherPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getWeatherPageTitle() throws InterruptedException {
		oBrowserUtil.waitForElementVisible(driver, searchTab, 5);
		return driver.getTitle();
	}

	public void enterCity() {
		if (checkCity(Citylist)) {
			System.out.println("Entered city available to checking weather details");
		} else {
			System.out.println("Entered city not available, searching for default city");
			searchTab.sendKeys(System.getProperty("DefaultCity"));
			driver.findElement(By.id(defaultCity)).click();
		}
	}

	public boolean checkCity(List<WebElement> list) {
		for (WebElement ele : list) {
			String city = ele.getAttribute("for");
			if (ele.getAttribute("for").equals(System.getProperty("city"))) {
				searchTab.sendKeys(System.getProperty("city"));
				driver.findElement(By.id(city)).click();
				return true;
			}
		}
		return false;
	}

	public String getWeather() throws InterruptedException {
		try {
			driver.findElement(By.xpath("//div[text()='" + city + "']")).click();
			oBrowserUtil.waitForElementVisible(driver, getWeatherDetails, 10);
			return getWeatherDetails.getText();
		} catch (Exception e) {
			driver.findElement(By.xpath("//div[text()='" + defaultCity + "']")).click();
			oBrowserUtil.waitForElementVisible(driver, getWeatherDetails, 10);
			return getWeatherDetails.getText();
		}
	}
}
