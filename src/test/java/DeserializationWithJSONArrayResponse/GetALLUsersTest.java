package DeserializationWithJSONArrayResponse;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetALLUsersTest {
	

	@Test
	public void getAllUsersTest() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		Response response = RestAssured
		.given()
				.header("Authorization", "Bearer 5a537bba01c7d33ef75af0d5fe15d3cca1f7df54eeec9599af160e0ff9619a5c")
		.when()
				.get("/public/v2/users/");
		
		
		response.prettyPrint();
		
		Assert.assertEquals(response.statusCode(), 200);
		
		//JSON Array ---> UserLombok POJO class
		//using ObjectMapper
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			UserLombok[] users = mapper.readValue(response.getBody().asString(), UserLombok[].class);
			
			for(UserLombok user : users) {
				System.out.println("id: "+ user.getId());
				System.out.println("name: "+ user.getName());
				System.out.println("status: "+ user.getStatus());
				System.out.println("email: "+ user.getEmail());
				System.out.println("gender: "+ user.getGender());
				System.out.println("------------");
			}
			
			
			
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		
		
	}


}
