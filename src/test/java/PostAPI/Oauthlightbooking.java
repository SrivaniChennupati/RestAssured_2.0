package PostAPI;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.util.List;

public class Oauthlightbooking {
	
	
	
	@Test
	public void getRequestToken() {
		
		RestAssured.baseURI="https://test.api.amadeus.com/";
		
		String token=RestAssured.given().log().all()
		.when().log().all()
		.header("Content-Type", "application/x-www-form-urlencoded")
		.formParam("grant_type", "client_credentials")
		.formParam("client_id", "rddb54Be5FI1EtkoJ8GlDF91oKXG2xil")
		.formParam("client_secret", "RqRnBDGMU4A4SgaC")
		.post("v1/security/oauth2/token")
		.then().log().all()
		.extract()
		.path("access_token");
		
		System.out.println(token);
		
		Response response=given()
		.when()
		.queryParam("origin", "PAR")
		.queryParam("maxPrice", "200")
		.header("Authorization","Bearer "+token)
		.get("v1/shopping/flight-destinations");
		
		
		JsonPath jsonbodyresponse=response.jsonPath();
		
		List<String> typelist=jsonbodyresponse.getList("data.type");
		
		for (String e : typelist) {
			
			System.out.println(e);
		}
		
	}

}
