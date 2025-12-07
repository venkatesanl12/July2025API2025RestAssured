package POSTAPITests;

import org.testng.annotations.Test;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import java.nio.file.Files;



public class CreateUserAPITest {
	
	public String getRandomEmailid() {
		return "apiautomation"+System.currentTimeMillis()+"@opencart.com";
	}
	
	
	
	@Test
	public void createUserWithJsonStringTest() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		String emailId = getRandomEmailid();
		
		given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization", "Bearer d0bf1714ac04c10dd2982e009d2dffe694a8e0b53af518cb7370e41e046a72f6")
			.body("{\n"
					+ "    \"name\": \"Api Automation\",\n"
					+ "    \"gender\": \"male\",\n"
					+ "    \"email\": \""+emailId+"\",\n"
					+ "    \"status\": \"active\"    \n"
					+ "}")
	    .when()
	    	.post("/public/v2/users")
	    .then().log().all()
	    	.assertThat()
	    		.statusCode(201);
	}

	@Test
	public void createUserWithJsonFileTest() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		String emailId = getRandomEmailid();
		
		given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization", "Bearer d0bf1714ac04c10dd2982e009d2dffe694a8e0b53af518cb7370e41e046a72f6")
			
			.body(new File("./src/test/resource/jsons/user.json"))
	    .when()
	    	.post("/public/v2/users")
	    .then().log().all()
	    	.assertThat()
	    		.statusCode(201);
	}
	
	@Test
	public void createUserWithJsonFileWithEmailReplacementTest() throws IOException {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		String emailId = getRandomEmailid();
		
		//convert the json file content to string:
		String rawJson = new String(Files.readAllBytes(Paths.get("./src/test/resource/jsons/user.json")));
		String updatedJson = rawJson.replace("{{email}}", emailId);		
		
		int userId = given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization", "Bearer d0bf1714ac04c10dd2982e009d2dffe694a8e0b53af518cb7370e41e046a72f6")
			.body(updatedJson)
	    .when()
	    	.post("/public/v2/users")
	    .then().log().all()
	    	.assertThat()
	    		.statusCode(201)
	    			.extract()
	    				.path("id");
		System.out.println("user id: "+ userId);
	}

	
	
	//4. POJO ---> Plain Old Java objects - Java Classes -- Serialization ---> DeSerialization ---> jackson + lombok api + builder patterns
	//5. JsonPath - Jayway JsonPath API - json queries
	

}
