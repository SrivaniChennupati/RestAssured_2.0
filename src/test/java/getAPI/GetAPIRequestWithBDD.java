package getAPI;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.hamcrest.core.IsNot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class GetAPIRequestWithBDD {
	
	
	
	@Test
	public void GetAPIRequestWithBDD() {
		
		given().log().all()
		.when().log().all()
		.get("https://gorest.co.in/public/v2/users")
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.and()
		.contentType(ContentType.JSON)
		.and()
		.body("$.size()", equalTo(10)) // $ represnts whole body 
		.and()
		.body("id", is(notNullValue()));
	

	}
	
	
	@Test
	
	public void getProductDataAPI_ExtractBody_JsonPath_JsomArray() {
		
		RestAssured.baseURI= "https://fakestoreapi.com/";
		
		RequestSpecification request=RestAssured.given();
		
		request.queryParam("limit", 10);
		
		Response response=request.get("products");
		
		// get all the title
		
		JsonPath jsonresponse=response.jsonPath();
		
		// since the response is an Json Array , we have to store all the ID's , we have to use getList
		
		List<Object> titlelist=jsonresponse.getList("title");
		
		List<Object> pricelist=jsonresponse.getList("price");
		
		List<Object> ratingrates=jsonresponse.getList("rating.rate");
		
		
		for (int i=0;i<=titlelist.size()-1;i++) {
			
			/*System.out.println(titlelist.get(i));
			
			System.out.println(pricelist.get(i));
			
			System.out.println(ratingrates.get(i));*/
			
			
			 Object title= titlelist.get(i);
			 
			 Object price=pricelist.get(i);
			 
			 Object Ratings=ratingrates.get(i);
			 
			 
			 System.out.println("title:"+title+" price: "+price+"Ratings: "+Ratings);
			 
			
			
		}
		
		
		
	}

     @Test
	 public void getProductDataAPI_ExtractBody_JSONpath() {
		

 		RestAssured.baseURI= "https://fakestoreapi.com/";
 		
 		RequestSpecification request=RestAssured.given();
 		
 		request.queryParam("limit", 10);
 		
 		Response response=request.get("products");
 		
 		JsonPath jsonresponse=response.jsonPath();
 		
 		// get the first id , since id is an Integer
 		
 		int id=jsonresponse.getInt("[0].id");
 		
 		 System.out.println(id);
 		 
 		 // since category is String
 		 
 		String category=jsonresponse.getString("[0].category");
 		
 		System.out.println(category);
 		
 		Float rate=jsonresponse.getFloat("[0].rating.rate");
 		
 		System.out.println(rate);
 		
 		
	}
     
     @Test
     public void getProductDataAPI_ExtractBody_Extract() {
    	 
    	 RestAssured.baseURI= "https://fakestoreapi.com/";
    	 
    	 String Description=given().log().all()
    	 .queryParam("limit", 10)
    	 .when().log().all()
    	 .get("products")
    	 .then().log().all()
    	 .assertThat()
    	 .statusCode(200)
    	 .and()
    	 .extract().path("[0].description");
    	 
    	 System.out.println(Description);
    	
    	 
    	 
     }
     
     @DataProvider
     public Object[][] getData() {
    	 
    	 return new Object[][] {
    		 
    		 {"2016",21},
    		 {"2018",21},
    		 {"2020",14},
    		 
    		 
    	 };
     }
     
     
     
     
     @Test (dataProvider="getData")
     public void getCircuitData_With_DataDriven(String year, int circuits) {
    	 
    	 RestAssured.baseURI= "http://ergast.com/";
    	 
    	RequestSpecification request= RestAssured.given();
    	
    	request.pathParam("year", year);
    	
    	Response response=request.get("api/f1/{year}/circuits.json");
    	
    	
    	JsonPath jsondata=response.jsonPath();
    	
    	int circuits1=jsondata.getInt("MRData.total");
    	
    	System.out.println(circuits1);
    	
    	Assert.assertEquals(circuits1,circuits );
    	 
     }
	

}
