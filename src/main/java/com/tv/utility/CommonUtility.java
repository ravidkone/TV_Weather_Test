package com.tv.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class CommonUtility{

	FileInputStream fis;
	Properties prop = new Properties();
	Logger log = Logger.getLogger(getClass().getSimpleName());

	public void loadConfigProperty(String sConfigPath) throws Exception {
		log.info("Current dir using System:" + sConfigPath);
		FileInputStream fis = new FileInputStream(sConfigPath);
		prop.load(fis);
		System.getProperties().putAll(prop);
	}

	public void loadLog4jProperty(String sLog4jPath) throws Exception {
		log.info("Current dir using System:" + sLog4jPath);
		FileInputStream fis = new FileInputStream(sLog4jPath);
		prop.load(fis);
		System.getProperties().putAll(prop);
		PropertyConfigurator.configure(prop);
	}

	public static String takeScreenShotWebReturnPath(WebDriver driver, String getMethodName) throws IOException {
		String sDestDir = "/screenshots/";
		String sImageName = System.getProperty("user.dir") + sDestDir + getMethodName + ".jpg";
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
				.takeScreenshot(driver);
		ImageIO.write(screenshot.getImage(), "jpg", new File(sImageName));
		System.out.println(sImageName);
		return sImageName;
	}

	// To add message in extent report
	public boolean verifyEqual(String actual, String expected, String message, ExtentTest extLogger) {

		String[][] data = { { "Message", message }, { "Actual", actual }, { "Expected", expected } };
		Markup m = MarkupHelper.createTable(data);

		if (actual.equals(expected)) {
			extLogger.pass(m);
			return true;
		} else {
			extLogger.fail(m);
		}
		return false;
	}

	public String generateURL(String URI) {
		if (TestBase.sHost == null) {
			TestBase.sHost = System.getProperty("stageHost");
			return TestBase.sHost + URI;
		} else {
			return TestBase.sHost + URI;
		}
	}

	public HashMap<String, String> getCityDetails() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("q", "Kanpur");
		params.put("appid", "7fe67bf08c80ded756e598d6f8fedaea");
		return params;
	}
	
	public String compare(Double t1, Double t2) {
		System.out.println("UI Temp: " + t1);
		System.out.println("API Temp: " + t2);
		System.out.println("Tempraure Differance value is: " + (t1-t2));
		if ((t1-t2) == 0 || (t1-t2) < -2 ||(t1-t2) < 2) {
			return "Pass";
		} else {
			return "Fail";
		}

	}
}
