package PostAPI;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.util.Random;

import org.testng.annotations.Test;

import POJO.CreateUser;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GoRest_CreateUser_With_POJO {
	
	
	public String getRandomMail() {
		
		Random random = new Random();
		
	    String Email = "test"+random.nextInt()+"@gmail.com";
	    
	    return Email;
		
	}

	@Test
	public void createUser() {
		
		RestAssured.baseURI="https://gorest.co.in/";
		
		CreateUser user = new CreateUser("Revanth Makkena", getRandomMail() , "active", "female");
		
	   int id= given().log().all()
		.when().log().all()
		.contentType(ContentType.JSON)
		.header("Authorization","Bearer 147dbb1eb6121f90b9b6bbff1839d63c6d47cecf2b4f0735adb4256f46e18f27")
		.body(user)
		.post("public/v2/users")
		.then().log().all()
		.extract()
		.path("id");
	   
	
	   
	   
	    
	  
	}
	
	
}
