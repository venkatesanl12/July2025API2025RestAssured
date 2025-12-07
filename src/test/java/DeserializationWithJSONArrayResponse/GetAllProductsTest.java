package DeserializationWithJSONArrayResponse;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetAllProductsTest {
	
	@Test
	public void getAllUsersTest() {
		
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		Response response = RestAssured
		.given()
		.when()
				.get("/products");
		
		
		response.prettyPrint();
		
		Assert.assertEquals(response.statusCode(), 200);
		
		//JSON Array ---> UserLombok POJO class
		//using ObjectMapper
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			ProductLombok[] products = mapper.readValue(response.getBody().asString(), ProductLombok[].class);
			
			for(ProductLombok product : products) {
				System.out.println("id : " + product.getId());
				System.out.println("Title : " + product.getTitle());
				System.out.println("Price : " + product.getPrice());
				System.out.println("Description : " + product.getDescription());
				System.out.println("Image : " + product.getImage());
				System.out.println("Category : " + product.getCategory());
				
				System.out.println("rate: "+ product.getRating().getRate());
				System.out.println("count: "+ product.getRating().getCount());
				
				System.out.println("-----------");

			}
			
			
			
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}


		
	}

}
