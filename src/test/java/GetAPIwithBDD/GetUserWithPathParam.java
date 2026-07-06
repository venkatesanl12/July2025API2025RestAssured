package GetAPIwithBDD;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;
public class GetUserWithPathParam {
	

		
		
		@Test
		public void getSingleUsersTest() {
			
			
			//BDD + Non BDD
			RestAssured.baseURI = "https://gorest.co.in";
			
			//https://gorest.co.in/public/v2/users/8135702
			given().log().all()
				.header("Authorization", "Bearer 5a537bba01c7d33ef75af0d5fe15d3cca1f7df54eeec9599af160e0ff9619a5c")
				.pathParam("userId", 8153038)
			.when()
				.get("/public/v2/users/{userId}")
			.then().log().all()
				.assertThat()
					.statusCode(200);
		}
		
		//https://reqres.in/:apiname/:resource?page=2
		//https://reqres.in/api/users?page=2
		
		@Test
		public void getAllUsersWithQueryAndPathParamsTest() {
					
			RestAssured.baseURI = "https://reqres.in";
			
			Map<String, String> pathParamMap = new HashMap<String, String>();
			pathParamMap.put("apiname", "api");
			pathParamMap.put("resource", "users");
			
			Map<String, Integer> queryParamMap = new HashMap<String, Integer>();
			queryParamMap.put("page", 2);

			given().log().all()
				.pathParams(pathParamMap)
				.queryParams(queryParamMap)
			.when()
				.get("/{apiname}/{resource}")
			.then().log().all()
				.assertThat()
					.statusCode(200);
		}
}
