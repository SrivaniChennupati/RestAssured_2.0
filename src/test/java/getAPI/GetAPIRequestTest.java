package getAPI;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAPIRequestTest {
	
	
	
	@Test
	public void getAPItestwithnonBDD() {
		
		
		RestAssured.baseURI = "https://gorest.co.in/";
		
		RequestSpecification Request =RestAssured.given();
		
	     // no headers we are passing
		
		Response response =Request.get("public/v2/users");
		
		
		int statuscode =response.getStatusCode();
		
		System.out.println(statuscode);
		
		
		// get all the response headers 
		
		List<Header> list=response.getHeaders().asList();
		
		for (Header e : list) {
			
		     System.out.println(e);
		}
		
		// get a single response header 
		
		String headerresponse=response.getHeader("Content-Type");
		
		System.out.println(headerresponse);
		
		
	}
	
	
	@Test
	public void getAPIRequestTestWithQueryParam() {
		
	    RestAssured.baseURI= "https://gorest.co.in/";
	    
	    RequestSpecification request=RestAssured.given();
	    
	    request.queryParam("name", "Bhaanumati Jain");
	    
	    request.queryParam("status", "inactive");
	    
	   Response response= request.get("public/v2/users");
	   
	  int statuscode= response.getStatusCode();
	  
	   System.out.println(statuscode);
	 
	  

	}
	
	@Test
	public void getAPIRequestTestWithHashMap() {
		
		   RestAssured.baseURI= "https://gorest.co.in/";
		    
		    RequestSpecification request=RestAssured.given();
		    
		    Map<String,String> querymap = new HashMap<String,String>();
		    
		    querymap.put("name", "Bhaanumati Jain");
		    
		    querymap.put("status", "inactive");
		    
		    request.queryParams(querymap);
		    
		    Response response= request.get("public/v2/users");
			   
			  int statuscode= response.getStatusCode();
			  
			   System.out.println(statuscode);
			 
	}
	
	

}
