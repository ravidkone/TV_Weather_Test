package com.tv.testcases;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import org.testng.annotations.Test;

import com.tv.utility.TestBase;

public class TempComparator extends TestBase{

	WeatherTest uiWeather = new WeatherTest();
	APIWeatherTest apiWeather = new APIWeatherTest();
	LinkedList<TempratureDetails> TempDetails;


	@Test
	public void test() {

		String res = compare(uiWeather.UI_Temp, apiWeather.API_Temp);
		System.out.println("result is:" + res);
	}

	public static String compare(Double t1, Double t2) {
		System.out.println("UI Temp: " + t1);
		System.out.println("API Temp: " + t2);
		System.out.println("value is: " + Double.compare(t1, t2));
		if (Double.compare(t1, t2) == 0 || Double.compare(t1, t2) < -0.9 || Double.compare(t1, t2) > 0.9) {
			return "Pass";
		} else {
			return "Fail";
		}

	}

}
