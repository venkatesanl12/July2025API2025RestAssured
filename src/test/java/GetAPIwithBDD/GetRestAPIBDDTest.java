package GetAPIwithBDD;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.* ;

public class GetRestAPIBDDTest {

	@Test
	
	public void getSingleUserTest() {
		
		//BDD + Non BDD
				RestAssured.baseURI = "https://gorest.co.in";
				
				Response response =  given().log().all()
										.header("Authorization", "Bearer 5a537bba01c7d33ef75af0d5fe15d3cca1f7df54eeec9599af160e0ff9619a5c")
												.when().log().all()
													.get("/public/v2/users/8153038");
				
				
				System.out.println("status code: "+ response.statusCode());
				System.out.println("status line: "+ response.statusLine());
				
				Assert.assertEquals(response.statusCode(), 200);
				Assert.assertTrue(response.statusLine().contains("200 OK"));
				
				response.prettyPrint();
				
				String contentType = response.getHeader("Content-Type");
				System.out.println("Content type: "+ contentType);
				Assert.assertEquals(contentType, "application/json; charset=utf-8");
				
				
				//fetch the json response body:
				JsonPath js = response.jsonPath();
				System.out.println("JSON RESPONSE PATH: "+ js) ;
				int userId = js.getInt("id");
				System.out.println("user id : "+ userId);
				Assert.assertEquals(userId, 8153038);
				
				String userName = js.getString("name");
				System.out.println("user name : "+ userName);
				Assert.assertEquals(userName, "Himani Ganaka");
				
				String userStatus = js.getString("status");
				System.out.println("user status : "+ userStatus);
				Assert.assertEquals(userStatus, "active");
				
				String email = (String)js.get("email");
				System.out.println("user email : "+ email);
				
		
		
	}
	
	@Test
	public void authTest() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		 given().log().all()
			.header("Authorization", "Bearer ---naveen")
		.when().log().all()
			.get("/public/v2/users")
		.then().log().all()
			.assertThat()
				.statusCode(401)
				.and()
				.statusLine("HTTP/1.1 401 Unauthorized");

}
	
}
