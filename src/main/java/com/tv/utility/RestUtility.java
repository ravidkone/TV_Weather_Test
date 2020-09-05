package com.tv.utility;

import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestUtility {
	
	
	public Response ufGet(String sURL, Map<String,String> parametersMap ) throws Exception{
		RestAssured.baseURI=sURL;
		Response res;
		if(parametersMap.isEmpty())
			res = RestAssured.given().get();
		else
			res = RestAssured.given().params(parametersMap).contentType("application/json").get();

		return res;
	}

}
