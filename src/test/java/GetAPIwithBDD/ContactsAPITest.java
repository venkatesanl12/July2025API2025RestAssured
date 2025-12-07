package GetAPIwithBDD;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class ContactsAPITest {
	
	@Test
	public void getAllContactsTest() {
		
		RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
		
		given().log().all()
			.header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2ODg5ODdlYzU4Mzg2OTAwMTVhNDE4Y2IiLCJpYXQiOjE3NTg1MTAxMzF9.qYv9xIZJmwzn0tD_F4Xiqqs-sMtlcmIrsftyvCdE9Yw")
		.when()
			.get("/contacts")
		.then().log().all()
			.assertThat()
				.statusCode(200)
					.and()
						.contentType(ContentType.JSON);
	}
	
	@Test
	public void getAuthErrorTest() {
		
		RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
		
		given().log().all()
			.header("Authorization", "Bearer --naveen")
		.when()
			.get("/contacts")
		.then().log().all()
			.assertThat()
				.statusCode(401);
					
	}
	
	@Test
	public void getAuthErrorMessageTest() {
		
		RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
		
		String errorMessg = given().log().all()
			.header("Authorization", "Bearer --naveen")
		.when()
			.get("/contacts")
		.then().log().all()
			.extract()
				.path("error");
		
		System.out.println("error Message : "+ errorMessg);
		Assert.assertEquals(errorMessg, "Please authenticate.");
					
	}
	
	

}
