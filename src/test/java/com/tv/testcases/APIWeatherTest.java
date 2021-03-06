package com.tv.testcases;

import static org.testng.Assert.assertEquals;
import java.util.HashMap;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.tv.utility.Constants;
import com.tv.utility.TestBase;
import io.restassured.response.Response;

public class APIWeatherTest extends TestBase {

	String sURL;
  Response res;
	 double API_Temp;
	Logger log = Logger.getLogger(getClass().getSimpleName());
	
	@BeforeClass
	public void generateURL_MemberDetails() throws Exception {
				sURL = oCommon.generateURL(System.getProperty("weather"));
		System.out.println("URI is: "+sURL);
		res = oRestUtil.ufGet(sURL, oCommon.getCityDetails());
	}

	@Test(priority = 1)
	public void verifyStatusCode() {
		assertEquals(res.getStatusCode(), Constants.iHTTPCode200,
				"Expected Response Code 200 and actual is " + res.getStatusCode());
	}
	
	@Test(priority=2)
	public void getTemprature() throws Exception {
	String	temprature=res.jsonPath().getString("main.temp");
	double t=273.15;
	double t1=Double.parseDouble(temprature);
	API_Temp=t1-t; 
	System.out.println("API Temp in degree: "+API_Temp);
	System.out.println("API Temprature added to list");
	res.prettyPrint();
	}

}
