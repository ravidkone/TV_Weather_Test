package com.tv.utility;

public class Constants {

	//Environment and Running app (to avoid driver.quit error)
	public static final String AutomationWeb = "Web", AutomationAPI = "API";
	public static final String Environment = "prod", contentType = "application/json";
	
	//HTTP Error codes for status return validation
	public static final int iHTTPCode201=201,iHTTPCode400=400,iHTTPCode200=200,iHTTPCode203=203,iHTTPCode404=404,
			iHTTPCode401=401,iHTTPCode405=405,iHTTPCode418=418,iHTTPCode403=403;
	
	public static final String HomePageTitle="NDTV: Latest News, India News, Breaking News, Business, Bollywood, Cricket, Videos & Photos";
	public static final String WeatherPageTitle ="NDTV Weather - Weather in your Indian City";
}
