package UpdateUserWithPOJO;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UpdateUserUsingPOJOTest {
	
	public String getRandomEmailid() {
		return "apiautomation"+System.currentTimeMillis()+"@opencart.com";
	}
	@Test
	public void updateUserTest() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		String emailId = getRandomEmailid();
		//create object of User class:
		User user = new User("Amit", emailId, "male", "active");
		
		
		System.out.println("----------------------1. POST CALL ----------------------");

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
		
		
		System.out.println("----------------------2. GET CALL ----------------------");
		
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
					.body("email", equalTo(emailId));
		
		System.out.println("----------------------3. PUT CALL ----------------------");

		//update the user info using setters
		user.setName("Amit Automation");
		user.setStatus("inactive");
		
		//3. update(put) a user by using the same userid 
		given().log().all()
			.header("Authorization", "Bearer 5a537bba01c7d33ef75af0d5fe15d3cca1f7df54eeec9599af160e0ff9619a5c")
			.contentType(ContentType.JSON)
			.body(user) //auto serialization: java object to json using jackson databind lib
		.when()	
			.put("/public/v2/users/"+userId)
		.then().log().all()
			.assertThat()
				.statusCode(200)
				.and()
					.body("id", equalTo(userId))
					.body("name", equalTo(user.getName()))
					.body("email", equalTo(emailId))
					.body("status", equalTo(user.getStatus()));
		
		
		System.out.println("----------------------4. GET CALL ----------------------");
		
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
					.body("email", equalTo(emailId))
					.body("status", equalTo(user.getStatus()))
					.body("gender", equalTo(user.getGender()));
	}
}


