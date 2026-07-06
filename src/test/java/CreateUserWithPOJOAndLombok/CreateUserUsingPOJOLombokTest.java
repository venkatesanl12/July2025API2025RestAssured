package CreateUserWithPOJOAndLombok;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateUserUsingPOJOLombokTest {
	public String getRandomEmailid() {
		return "apiautomation"+System.currentTimeMillis()+"@opencart.com";
	}
	@Test

	public void createUserTest() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		String emailId = getRandomEmailid();
		
		//create object of UserLombok POJO class:
		UserLombok user = new UserLombok("Karim", emailId, "male", "active");
		
		int userId = given().log().all()
				.contentType(ContentType.JSON)
				.header("Authorization", "Bearer 5a537bba01c7d33ef75af0d5fe15d3cca1f7df54eeec9599af160e0ff9619a5c")
				.body(user) //auto serialization: java object to json using jackson databind lib
			.when()
				.post("/public/v2/users")
		    .then().log().all()
		    	.assertThat()
		    		.statusCode(201)
		    			.extract()
		    				.path("id");
			
			System.out.println("user id : " + userId);
			

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
						.body("name", equalTo(user.getName()))
						.body("status", equalTo(user.getStatus()))
						.body("email", equalTo(emailId));
		
	}
	
	@Test
	public void createUserWithPOJOLombokBuilderTest() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		String emailId = getRandomEmailid();
		
		//create object of UserLombok POJO class:
		//UserLombok user = new UserLombok("Karim", emailId, "male", "active");
		
		//create object of UserLombok POJO class using builder pattern:
		
		UserLombok user = new UserLombok.UserLombokBuilder()
							.name("TomVenkat")
							.status("active")
							.email(emailId)
							.gender("male")
							.build();
		
		
										
		int userId = given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization", "Bearer 5a537bba01c7d33ef75af0d5fe15d3cca1f7df54eeec9599af160e0ff9619a5c")
			.body(user) //auto serialization: java object to json using jackson databind lib
		.when()
			.post("/public/v2/users")
	    .then().log().all()
	    	.assertThat()
	    		.statusCode(201)
	    			.extract()
	    				.path("id");
		
		System.out.println("user id : " + userId);
		
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
					.body("name", equalTo(user.getName()))
					.body("status", equalTo(user.getStatus()))
					.body("email", equalTo(emailId));
		
	}

}
