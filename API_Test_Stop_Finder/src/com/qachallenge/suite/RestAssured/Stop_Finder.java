package com.qachallenge.suite.RestAssured;

import org.testng.annotations.Test;

import Jsonmappedclasses.APIList;
import Jsonmappedclasses.GenericClass;
import Utilities.CommonFunctions;
import bsh.org.objectweb.asm.Type;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;




import org.testng.Assert;





import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.FileNotFoundException;

import org.testng.Assert;
import org.testng.annotations.Test;




import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;

public class Stop_Finder {
	APIList api;
	//Url Initialization
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
		System.out.println("api.stopFinder in verify status"+api.stopFinder);
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
	
	
}
