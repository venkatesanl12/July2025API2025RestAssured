
package FakeUserAPITest;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import FakeUserAPITest.FakeUser.Address;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateAFakeUserTest {
	
	
	
	@Test
	public void createAPetTest() {
		
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		//create the object of FakeUser class:
		
		Address.GeoLocation geoLocation = new Address.GeoLocation("-37.3159", "81.1496");
		
		FakeUser.Address address = new FakeUser.Address("NY", "7th street", 9090, "51645", geoLocation);
		
		FakeUser.Name name = new FakeUser.Name("Peter", "Automation");
		
		FakeUser user = new FakeUser("Peter@mail.com", "peterAPI", "peter@123", "1-570-236-7033", name, address);
		
		
		given().log().all()
		.contentType(ContentType.JSON)
		.body(user) //serialization : 
	.when()
		.post("/users")
	.then().log().all()
		.statusCode(201);
		
		
	}
	
	

}
