package GetAPIwithBDD;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.AssertJUnit;
import static io.restassured.RestAssured.given;

import java.util.List;

public class GoRestAPIsTest {
	
	@Test
	public void getSingleUsersTest() {
		
		
		//BDD + Non BDD
		RestAssured.baseURI = "https://gorest.co.in";
		
		Response response =  given().log().all()
								.header("Authorization", "Bearer 941772c939576f3e1998a1ddfb7ce810dc899247e089ef002b2f78eb32750349")
										.when().log().all()
											.get("/public/v2/users/8153038");
		
		
		System.out.println("status code: "+ response.statusCode());
		System.out.println("status line: "+ response.statusLine());
		
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertTrue(response.statusLine().contains("200 OK"));
		
		response.prettyPrint();
		
		String contentType = response.getHeader("Content-Type");
		System.out.println("Content type: "+ contentType);
		AssertJUnit.assertEquals(contentType, "application/json; charset=utf-8");
		
		
		//fetch the json response body:
		JsonPath js = response.jsonPath();
		int userId = js.getInt("id");
		System.out.println("user id : "+ userId);
		AssertJUnit.assertEquals(userId, 8153038);
		
		String userName = js.getString("name");
		System.out.println("user name : "+ userName);
		Assert.assertEquals(userName, "Himani Ganaka");
		
		String userStatus = js.getString("status");
		System.out.println("user status : "+ userStatus);
		AssertJUnit.assertEquals(userStatus, "active");
		
		String email = (String)js.get("email");
		System.out.println("user email : "+ email);
		
	
		
		
	}
	
	@Test
	public void getAllUsersTest() {

	//BDD + Non BDD
	RestAssured.baseURI = "https://gorest.co.in";
	
	Response response =  given().log().all()
							.header("Authorization", "Bearer d0bf1714ac04c10dd2982e009d2dffe694a8e0b53af518cb7370e41e046a72f6")
									.when().log().all()
										.get("/public/v2/users");
	
	
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
	
	List<Integer> idList = js.getList("id");
	System.out.println(idList);
	
	List<String> nameList = js.getList("name");//0-9:10
	System.out.println(nameList);

	for(String e : nameList) {
		System.out.println(e);
		Assert.assertNotNull(e);
	}

}
}
