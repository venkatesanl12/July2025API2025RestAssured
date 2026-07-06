package CreateUserWithPostAPI;

import java.io.IOException;
import static org.hamcrest.Matchers.equalTo;
import java.nio.file.Paths;
import java.nio.file.Files;
import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateUserAPITest {
	
	public String getRandomEmailid() {
		return "apiautomation"+System.currentTimeMillis()+"@opencart.com";
	}
	
	@Test
	public void createAUserTest() throws IOException {
		
		
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		String emailId = getRandomEmailid();
		
		System.out.println("----------------------EMAIL ID  ----------------------"+emailId);
		
		
		//convert the json file content to string:
		String rawJson = new String(Files.readAllBytes(Paths.get("./src/test/resource/jsons/user.json")));
		String updatedJson = rawJson.replace("{{email}}", emailId);	
		
		System.out.println("----------------------updatedJson ----------------------"+updatedJson);
		System.out.println("----------------------POST CALL ----------------------");

		//1. post: create a user
		int userId = given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization", "Bearer 5a537bba01c7d33ef75af0d5fe15d3cca1f7df54eeec9599af160e0ff9619a5c")
			.body(updatedJson)
	    .when()
	    	.post("/public/v2/users")
	    .then().log().all()
	    	.assertThat()
	    		.statusCode(201)
	    			.extract()
	    				.path("id");
		
		System.out.println("user id: "+ userId);
		
		System.out.println("----------------------GET CALL ----------------------");
		
		//2. get a user by using the same userid 
		given().log().all()
			.header("Authorization", "Bearer 5a537bba01c7d33ef75af0d5fe15d3cca1f7df54eeec9599af160e0ff9619a5c")
		.when()
			.get("/public/v2/users/"+userId)
		.then().log().all()
			.assertThat()
				.statusCode(200)
				.and()
					.body("id", equalTo(userId))
					.body("name", equalTo("Api Automation"))
					.body("email", equalTo(emailId));	
					

  }
}
