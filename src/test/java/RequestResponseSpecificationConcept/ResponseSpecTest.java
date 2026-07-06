package RequestResponseSpecificationConcept;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecTest {
	
	@Test
	public void getUsersTest() {
		
		ResponseSpecification resSpec = RestAssured.expect()
					.statusCode(200)
					.header("Content-Type", "application/json; charset=utf-8")
					.header("Server", "cloudflare");
					
		
		RestAssured.given()
						.baseUri("https://gorest.co.in")
						.header("Authorization", "Bearer 5a537bba01c7d33ef75af0d5fe15d3cca1f7df54eeec9599af160e0ff9619a5c")
					.when()
						.get("/public/v2/users/")
					.then()
						.spec(resSpec);
		
		
		RestAssured.given()
					.baseUri("https://gorest.co.in")
					.header("Authorization", "Bearer 5a537bba01c7d33ef75af0d5fe15d3cca1f7df54eeec9599af160e0ff9619a5c")
				.when()
					.get("/public/v2/users/8209254")
				.then()
					.spec(resSpec);
							
		
	}
	

}
