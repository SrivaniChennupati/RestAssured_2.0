package SpecifactionsConcept;

import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.Matchers.*;
import io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ReqestSpecificationBuilder {
	
	
	public static RequestSpecification get_Req_Spec_builder() {

		RequestSpecification request = new RequestSpecBuilder().setBaseUri("https://gorest.co.in/")
				.setContentType(ContentType.JSON)
				.addHeader("Authorization", "Bearer 147dbb1eb6121f90b9b6bbff1839d63c6d47cecf2b4f0735adb4256f46e18f27")
				.build();
		
		
		return request;

	}
	
	
 public static ResponseSpecification get_Response_200Ok_Spec_Builder() {
		
		ResponseSpecification response=new ResponseSpecBuilder()
		.expectStatusCode(200)
		.expectContentType(ContentType.JSON)
		.build();
		
		return response;
	}
	
	
	
	public static ResponseSpecification get_Response_Spec_Builder() {
		
		ResponseSpecification response=new ResponseSpecBuilder()
		.expectStatusCode(200)
		.expectContentType(ContentType.JSON)
		.expectBody("$.size()",equalTo(10))
		.build();
		
		return response;
	}
	
	
	@Test
	
	public void GetUsersAPI() {
		
		// create the object of Request Specification builder 
		
		
		  RestAssured.given().log().all()
		 
		  .spec(get_Req_Spec_builder())
		  .when().log().all()
		  .get("public/v2/users")
		  .then().log().all()
		  .spec(get_Response_Spec_Builder());
		
	}

}
