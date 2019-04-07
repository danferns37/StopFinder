package com.qachallenge.suite.RestAssured;

import org.testng.annotations.Test;

import Jsonmappedclasses.APIList;
import Utilities.CommonFunctions;


import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;




import org.testng.Assert;





import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.testng.Assert;
import org.testng.annotations.Test;




import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Stop_Finder {
	APIList api;
	//Url Initialization
	 
	 Response  srvrResponse;
	
	@BeforeTest
	public void setup() throws FileNotFoundException  {
		APIList api =  CommonFunctions.<APIList>readJson("src//Config//APIlist.json",APIList.class); 
		//APIList api =  CommonFunctions.readJson1("src//Config//APIlist.json");
	    RestAssured.baseURI = "https://www.transportnsw.info";
	    RestAssured.baseURI = api.baseURI;
	    
	}

	// Verification of status of the get request on transport nsw api
	@Test
	public void verifystatus() throws FileNotFoundException {
		APIList api =  CommonFunctions.<APIList>readJson("src//Config//APIlist.json",APIList.class); 
		//APIList api =  CommonFunctions.readJson1("src//Config//APIlist.json");
		//System.out.println("api.stopFinder in verify status"+api.stopFinder);
		given()
		.log().all()
		
		.when()
			.get(api.stopFinder)
		
					
		.then()
			.statusCode(200).log().all();
	}

	//verification of the location ,stop and modes of transport
	@Test
	public void verifystopsmodes() throws InterruptedException, FileNotFoundException {

		
		APIList api =  CommonFunctions.<APIList>readJson("src//Config//APIlist.json",APIList.class); 
		
		given()		
		.log().all()
			
		.when()
		.get(api.stopFinder)
		.then()
			.statusCode(200)
			.and().body("locations.name", hasItems("Wynyard Station, Sydney")).and()
			.body("locations[0].assignedStops[0].name", equalTo("Wynyard Station")).and()
		.body("locations[0].assignedStops[0].modes",hasItems(1,5,11));
	}

	//validation of api response time
	@Test
	public void whenValidateResponseTime_thenSuccess() throws FileNotFoundException {
		APIList api =  CommonFunctions.<APIList>readJson("src//Config//APIlist.json",APIList.class); 
		//APIList api =  CommonFunctions.readJson1("src//Config//APIlist.json");
	    when().get(api.stopFinder).then().time(lessThan(5000L));
	}
	
	
	
	@Test
	public void whenValidateResponseTime1_thenSuccess() throws FileNotFoundException {
	APIList api =  CommonFunctions.<APIList>readJson("src//Config//APIlist.json",APIList.class); 
		
	/*locationname = 	(String)
			given()		
		.log().all()
			
		.when()
		.get(api.stopFinder)
		.then().extract().
			
			path("locations.name");
			*/
	
	
   srvrResponse = (Response) given().
            param("starttime", "2017-08-10T11:17:00").
            param("endtime", "2017-08-10T11:17:01").
            when().
            get(api.stopFinder).
            then().
            extract().
            response();
         

//System.out.println(srvrResponse.getStatusCode());
Assert.assertEquals(srvrResponse.getStatusCode(), 200);


	}
	

	
	@Test
	public void whenValidateResponseTime2_thenSuccess() throws FileNotFoundException {
	
		
		System.out.println("Hi,how are you?");
	String body    = srvrResponse.getBody().jsonPath().getString("locations[0].assignedStops[0].name");
	System.out.println("body"+body);
	Assert.assertEquals(body, "Wynyard Station");
	
  
	}

	


	
	
	
	
	
	
	
	
	
}
