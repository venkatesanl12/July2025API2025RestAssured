package GetAPIwithBDD;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;


public class GetUserWithQueryParam {
	
	@Test
	public void getUsersWithQueryParamTest() {	
	
	RestAssured.baseURI = "https://gorest.co.in";
	
	given().log().all()
		.header("Authorization", "Bearer d0bf1714ac04c10dd2982e009d2dffe694a8e0b53af518cb7370e41e046a72f6")
		.queryParam("name", "Ranjit")
		.queryParam("status", "active")
	.when()
		.get("/public/v2/users")
	.then().log().all()
		.assertThat()
		.statusCode(200)
	.and()	
		.contentType(ContentType.JSON);		

}
	
	@Test
	public void getUsersWithHashMapQueryParamTest() {		
		RestAssured.baseURI = "https://gorest.co.in";		
		
		Map<String, String> userQueryMap = new HashMap<String, String>();
		userQueryMap.put("name", "Ranjit");
		userQueryMap.put("status", "inactive");
		userQueryMap.put("gender", "male");
		
		given().log().all()
			.header("Authorization", "Bearer d0bf1714ac04c10dd2982e009d2dffe694a8e0b53af518cb7370e41e046a72f6")
			.queryParams(userQueryMap)
		.when()
			.get("/public/v2/users")
		.then().log().all()
			.assertThat()
			.statusCode(200)
		.and()	
			.contentType(ContentType.JSON);		
	}
	

	
}