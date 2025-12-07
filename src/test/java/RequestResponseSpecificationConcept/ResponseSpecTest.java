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
						.header("Authorization", "Bearer f6d59485d3d12d1df22e587ed2c283541957447b135c412bcfb79b73964a0429")
					.when()
						.get("/public/v2/users/")
					.then()
						.spec(resSpec);
		
		
		RestAssured.given()
					.baseUri("https://gorest.co.in")
					.header("Authorization", "Bearer f6d59485d3d12d1df22e587ed2c283541957447b135c412bcfb79b73964a0429")
				.when()
					.get("/public/v2/users/8209254")
				.then()
					.spec(resSpec);
							
		
	}
	

}
