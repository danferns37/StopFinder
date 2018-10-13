package com.qachallenge.suite.RestAssured;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;




import org.testng.Assert;





import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


import org.testng.Assert;
import org.testng.annotations.Test;




import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;

public class Stop_Finder {
	
	
	@BeforeTest
	public void setup() {
	    RestAssured.baseURI = "https://www.transportnsw.info";
	    //RestAssured.port = 443;
	}

	// Verification of Initial Request
	@Test
	public void verifystatus() {
		
		
		given()
		.log().all()
		
		.when()
			.get("/web/XML_STOPFINDER_REQUEST?TfNSWSF=true&language=en%20&name_sf=Wynyard%20Station&outputFormat=rapidJSON&type_sf=any&version=10.2.2.48")
		
					
		.then()
			.statusCode(200).log().all();
	}

	
	@Test
	public void verifystopsmodes() throws InterruptedException {
	
		given()		
		.log().all()
			
		.when()
		.get("/web/XML_STOPFINDER_REQUEST?TfNSWSF=true&language=en%20&name_sf=Wynyard%20Station&outputFormat=rapidJSON&type_sf=any&version=10.2.2.48")
		.then()
			.statusCode(200)
			.and().body("locations.name", hasItems("Wynyard Station, Sydney")).and()
			.body("locations[0].assignedStops[0].name", equalTo("Wynyard Station")).and()
		.body("locations[0].assignedStops[0].modes",hasItems(1,5,11));
	}

	
	@Test
	public void whenValidateResponseTime_thenSuccess() {
	    when().get("/web/XML_STOPFINDER_REQUEST?TfNSWSF=true&language=en%20&name_sf=Wynyard%20Station&outputFormat=rapidJSON&type_sf=any&version=10.2.2.48").then().time(lessThan(5000L));
	}
	
	
}
