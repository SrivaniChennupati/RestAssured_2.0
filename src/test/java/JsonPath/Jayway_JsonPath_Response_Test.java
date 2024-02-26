package JsonPath;

import java.util.List;

import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Jayway_JsonPath_Response_Test {
	
	@Test
	public void JsonPath_Response_Test1() {
		
		RestAssured.baseURI="https://fakestoreapi.com/";
		
		
		Response response=RestAssured.given().log().all()
		.when().log().all()
		.get("products");
		
		String JsonResponse=response.asString();
		
		System.out.println(JsonResponse);
		
		List<String>  titlelist=JsonPath.read(JsonResponse, "$..title");
		
		System.out.println(titlelist);
		
		for (String e :titlelist ) {
			
			System.out.println(e);
		}
		
	}

}
