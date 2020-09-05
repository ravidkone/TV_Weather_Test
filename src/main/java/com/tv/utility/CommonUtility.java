package com.tv.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

public class CommonUtility {

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
		if(URI.contains("/v1/"))
			URI.replaceAll("/v1/", System.getProperty("version"));
		return TestBase.sHost+URI;
	}

}
