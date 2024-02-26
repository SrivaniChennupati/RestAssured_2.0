package PostAPI;

import java.io.File;

import org.testng.annotations.Test;
import io.restassured.RestAssured.*;
import org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class postRequest {
	
	
	
	@Test
	
	public void generate_Auth_Token() {
		
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		
		RequestSpecification Request=RestAssured.given();
		
		Request.contentType(ContentType.JSON);
		
		Request.body(new File("./src/test/resources/JsonData/AuthToken.json"));
		
		Response response=Request.post("auth");
		
		JsonPath jsonbody=response.jsonPath();
		
		String tokenid=jsonbody.getString("token");
		
		
	}
	
	
	// add user test
	@Test
	public void addUserTest() {
		
		
		RestAssured.baseURI="https://gorest.co.in/";
		
		RequestSpecification request=RestAssured.given();
		
		request.header("Authorization","Bearer 147dbb1eb6121f90b9b6bbff1839d63c6d47cecf2b4f0735adb4256f46e18f27");
		
		request.contentType(ContentType.JSON);
		
		request.body(new File("./src/test/resources/JsonData/CreateUser.json"));
		
		Response response=request.post("public/v2/users");
		
		JsonPath jsonbody=response.jsonPath();
		
		int id=jsonbody.getInt("id");
		
		System.out.println(id);
		
		RestAssured.given()
		.when().log().all()
		.get("public/v2/users/"+id)
		.then().log().all()
		.assertThat()
		.statusCode(200);
	
		
	}

}
