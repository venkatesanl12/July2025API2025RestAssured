package RequestResponseSpecificationConcept;

import org.testng.annotations.Test;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSepcTest {
	
	@Test
	public void reqResSepcTest() {
		
		RequestSpecification requestSepc = 
							RestAssured.given().log().all()
								.baseUri("https://jsonplaceholder.typicode.com")
									.header("Content-Type", "application/json");
		
		
		//1. /posts 
		requestSepc.get("/posts")
					.then().log().all()
						.statusCode(200);
		
		//2. /comments:
				requestSepc.get("/comments")
							.then().log().all()
								.statusCode(200);
		
	   //3. create a post using post call : /posts
				requestSepc
						.body("{\n"
								+ "    \"userId\": 1,\n"
								+ "    \"title\": \"My Title\",\n"
								+ "    \"body\": \"I love API testing\"\n"
								+ "}")
						.post("/posts")
							.then().log().all()
								.statusCode(201);
	}
	
	@Test
	public void goRestAPITest() {
		RequestSpecification goRestRequestSpec = 
				RestAssured.given().log().all()
						.baseUri("https://gorest.co.in")
						.header("Content-Type", "application/json")
						.header("Authorization", "Bearer 5a537bba01c7d33ef75af0d5fe15d3cca1f7df54eeec9599af160e0ff9619a5c");
		
		
		goRestRequestSpec
					.get("/public/v2/users")
						.then().log().all()
							.statusCode(200);
		
		goRestRequestSpec
			.get("/public/v2/users/8209254")
				.then().log().all()
					.statusCode(200);
		
		
	}
	
	@Test
	public void goRestAPIWithQueryParamTest() {
		RequestSpecification goRestRequestSpec = 
				RestAssured.given().log().all()
						.baseUri("https://gorest.co.in")
						.queryParam("name", "naveen")
						.queryParam("status", "active")
						.header("Content-Type", "application/json")
						.header("Authorization", "Bearer 5a537bba01c7d33ef75af0d5fe15d3cca1f7df54eeec9599af160e0ff9619a5c");
						
		
		goRestRequestSpec
					.get("/public/v2/users")
						.then().log().all()
							.statusCode(200);
	}
	
	


}
