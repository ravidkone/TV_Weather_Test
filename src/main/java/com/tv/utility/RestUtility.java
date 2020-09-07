package com.tv.utility;

import java.util.Base64;
import java.util.Map;

import org.apache.log4j.Logger;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestUtility {
	
	public int iStatusCode=0;
	Logger log = Logger.getLogger(getClass().getSimpleName());

	public Response ufGet(String sURL, Map<String,String> parametersMap ) throws Exception{
		RestAssured.baseURI=sURL;
		Response res;
		if(parametersMap.isEmpty())
			res = RestAssured.given().get();
		else
			res = RestAssured.given().params(parametersMap).contentType("application/json").get();

		return res;
	}

	public Response ufGet(String sURL) throws Exception {
		log.info("setting Base URL as : "+sURL);
		RestAssured.baseURI=sURL;
		Response res = RestAssured.given().contentType(Constants.contentType).get();
		log.info(iStatusCode=res.getStatusCode());
		log.info(res.asString());
		return res;
		}

		/*
		* pre-requisite
		* 1. username and password in String array
		* 2. URL in String
		*
		* Converts username and password into base64 format and pass it to header as an basic authorization format
		*
		* */
		public Response ufGet(String sURL, String[] headerUsernamePassword) throws Exception{
		log.info("setting Base URL as : "+sURL+"\nParameter : "+headerUsernamePassword[0]+":"+headerUsernamePassword[1]);
		String authString = headerUsernamePassword[0] + ":" + headerUsernamePassword[1];
		byte[] base64Encoded = Base64.getEncoder().encode(authString.getBytes());
		String encodedString = new String(base64Encoded);
		System.out.println(encodedString);
		RestAssured.baseURI=sURL;
		Response res = RestAssured.given().header("Authorization", "Basic " + encodedString).contentType(Constants.contentType).get();
		log.info(iStatusCode=res.getStatusCode());
		log.info(res.asString());
		return res;
		}
		public Response ufGet(String sURL, Map<String,String> HeadersMap, Map<String,String> parametersMap ) throws Exception{
		RestAssured.baseURI=sURL;
		Response res;
		log.info(sURL);
		if(parametersMap.isEmpty())
		res = RestAssured.given().headers(HeadersMap).get();
		else
		res = RestAssured.given().headers(HeadersMap).params(parametersMap).contentType(Constants.contentType).get();

		log.info(iStatusCode=res.getStatusCode());
		log.info(res.asString());
		return res;
		}



		public Response ufGet(String sURL, Map<String,String> HeadersMap, String sBody ) throws Exception{
		RestAssured.baseURI=sURL;
		//contentType(Constants.sContentType) --->  Should be passed as part of Authorizaton
		Response res = RestAssured.given().headers(HeadersMap).body(sBody).get();
		log.info(iStatusCode=res.getStatusCode());
		log.info(res.asString());
		return res;
		}

	
}
