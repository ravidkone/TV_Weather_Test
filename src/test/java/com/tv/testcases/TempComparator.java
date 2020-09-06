package com.tv.testcases;

import java.util.Iterator;
import java.util.LinkedList;

import org.testng.annotations.Test;

public class TempComparator {

	WeatherTest uiWeather = new WeatherTest();
	APIWeatherTest apiWeather = new APIWeatherTest();
	LinkedList<TempratureDetails> TempDetails;

	public TempComparator() {
		TempDetails = new LinkedList<TempratureDetails>();
		TempDetails.add(new TempratureDetails("ravi", 33.00));
	}

	@Test
	public void test() {
		System.out.println("List of Temps");
		Iterator<TempratureDetails> it = TempDetails.iterator();
		while (it.hasNext()) {
			TempratureDetails tp = it.next();
			System.out.println("Name: " + tp.cityName + "  Temp is: " + tp.temp);
		}

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
