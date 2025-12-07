package PETAPIWithLombok;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import PETAPIWithLombok.Pet.Tag;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreatePetAPITest {
	
	
	@Test
	public void createAPetTest() {
		
		RestAssured.baseURI = "https://petstore.swagger.io";
		
		//create the object of Pet class:
		
		Pet.Category category = new Pet.Category(101, "Dog");
		
		List<String> photoUrls =  Arrays.asList("https://dog1.com", "https://dog2.com", "https://dog3.com");
		
		Pet.Tag tag1 = new Pet.Tag(201, "Red");
		Pet.Tag tag2 = new Pet.Tag(202, "Black");
		Pet.Tag tag3 = new Pet.Tag(202, "White");

		
		List<Tag> tags = Arrays.asList(tag1, tag2, tag3);
		
		Pet pet = new Pet(1, "Tommy", "available", photoUrls, tags, category);
		
		given().log().all()
			.contentType(ContentType.JSON)
			.body(pet) //serialization : POJO to JSON
		.when()
			.post("/v2/pet")
		.then().log().all()
			.statusCode(200);
	}
	
	@Test
	public void createAPetWithAssertionsTest() {
		
		RestAssured.baseURI = "https://petstore.swagger.io";
		
		//create the object of Pet class:
		
		Pet.Category category = new Pet.Category(101, "Dog");
		
		List<String> photoUrls =  Arrays.asList("https://dog1.com", "https://dog2.com", "https://dog3.com");
		
		Pet.Tag tag1 = new Pet.Tag(201, "Red");
		Pet.Tag tag2 = new Pet.Tag(202, "Black");
		Pet.Tag tag3 = new Pet.Tag(202, "White");

		
		List<Tag> tags = Arrays.asList(tag1, tag2, tag3);
		
		Pet pet = new Pet(1, "Tommy", "available", photoUrls, tags, category);
		
		Response response = given().log().all()
			.contentType(ContentType.JSON)
			.body(pet) //serialization : POJO to JSON
		.when()
			.post("/v2/pet");
		
		Assert.assertEquals(response.statusCode(), 200);
		
		JsonPath js = response.jsonPath();
		
		Assert.assertEquals(js.getInt("id"), pet.getId());
		Assert.assertEquals(js.getString("name"), pet.getName());
		Assert.assertEquals(js.getString("status"), pet.getStatus());

		Assert.assertEquals(js.getInt("category.id"), category.getId());
		Assert.assertEquals(js.getString("category.name"), category.getName());

		Assert.assertEquals(js.getList("photoUrls"), pet.getPhotoUrls());
		
//		Assert.assertEquals(js.getInt("tags[0].id"), pet.getTags().get(0).getId());
//		Assert.assertEquals(js.getInt("tags[1].id"), pet.getTags().get(1).getId());
//		Assert.assertEquals(js.getInt("tags[2].id"), pet.getTags().get(2).getId());

		for(int i=0; i<tags.size(); i++) {
			Assert.assertEquals(js.getInt("tags["+i+"].id"), pet.getTags().get(i).getId());
			Assert.assertEquals(js.getString("tags["+i+"].name"), pet.getTags().get(i).getName());

		}
		
		//jayway jsonpath lib - to fetch the data from complex json

}
	
	@Test
	public void createAPetUsingBuilderTest() {
		
		RestAssured.baseURI = "https://petstore.swagger.io";
		
		//create the object of Pet class using builder pattern:
		
		Pet.Category category = new Pet.Category.CategoryBuilder()
							.id(101)
							.name("Dog")
							.build();
		List<String> photoUrls =  Arrays.asList("https://dog1.com", "https://dog2.com", "https://dog3.com");
		
		Pet.Tag tag1 = new Pet.Tag.TagBuilder().id(201).name("Red").build();
		Pet.Tag tag2 = new Pet.Tag.TagBuilder().id(202).name("Black").build();
		List<Tag> tags = Arrays.asList(tag1, tag2);
		
		Pet pet = new Pet.PetBuilder()
					.id(1)
					.name("Tommy")
					.status("available")
					.photoUrls(photoUrls)
					.category(category)
					.tags(tags)
					.build();
		
		given().log().all()
			.contentType(ContentType.JSON)
			.body(pet) //serialization : POJO to JSON
		.when()
			.post("/v2/pet")
		.then().log().all()
			.statusCode(200);
	}
}
