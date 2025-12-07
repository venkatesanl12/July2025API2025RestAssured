package POSTAPIWithDifferentBodyTypes;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.io.File;

public class POSTAPIWithDifferentBodyTest {
	@Test
	public void bodyWithTextTest() {
		
		RestAssured.baseURI = "https://postman-echo.com";
		
		String payload = "Hi This is My Plain Code";
		
		given()
			.contentType(ContentType.TEXT)
			.body(payload)
		.when()
			.post("/post")
		.then()
			.assertThat()
				.statusCode(200);
	}
	@Test
	public void bodyWithHTMLTest() {
		
		RestAssured.baseURI = "https://postman-echo.com";
		
		String payload = "<html>\n"
				+ "<body>\n"
				+ "\n"
				+ "<h1>Heading 1</h1>\n"
				+ "<h2>Heading 2</h2>\n"
				+ "<h3>Heading 3</h3>\n"
				+ "<h4>Heading 4</h4>\n"
				+ "<h5>Heading 5</h5>\n"
				+ "<h6>Heading 6</h6>\n"
				+ "\n"
				+ "</body>\n"
				+ "</html>" ;
			
		given()
			.contentType(ContentType.HTML)
			.body(payload)
		.when()
			.post("/post")
		.then()
			.assertThat()
				.statusCode(200);
	}
	
	@Test
	public void bodyWithXMLTest() {
		
		RestAssured.baseURI = "https://postman-echo.com";
		
		String payload = "<dependency>\n"
				+ "    <groupId>io.rest-assured</groupId>\n"
				+ "    <artifactId>rest-assured</artifactId>\n"
				+ "    <version>5.5.6</version>\n"
				+ "</dependency>";
		
		given().log().all()
			.contentType("application/xml;charset=utf-8")
			.body(payload)
		.when()
			.post("/post")
		.then().log().all()
			.assertThat()
				.statusCode(200);
	}
	
	public String getRandomEmailid() {
		return "apiautomation"+System.currentTimeMillis()+"@opencart.com";
	}
	
	
	@Test
	public void bodyWithJSONTest() {
		
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
	public void bodyWithSinglePDFFileTest() {
		
		RestAssured.baseURI = "https://postman-echo.com";
		
		given().log().all()
			.contentType("application/pdf")
			.body(new File("/Users/v0l00xk/Downloads/42213591.pdf"))
			
		.when()
			.post("/post")
		.then().log().all()
			.assertThat()
				.statusCode(200);
	}
	
	@Test
	public void bodyWithFormDataMultipPartTest() {
		
		RestAssured.baseURI = "https://postman-echo.com";
		
		given().log().all()
			.contentType(ContentType.MULTIPART)
			.multiPart("name", "Tom")
			.multiPart("resume", new File("/Users/v0l00xk/Downloads/42213591.pdf"))
			.multiPart("photo", new File("/Users/v0l00xk/Downloads/PHOTO.jpeg"))

		.when()
			.post("/post")
		.then().log().all()
			.assertThat()
				.statusCode(200);
	}
	
	@Test
	public void bodyWithSingleCSVFileTest() {
		
		RestAssured.baseURI = "https://postman-echo.com";
		
		given().log().all()
			.contentType("text/csv")
			.body(new File("/Users/v0l00xk/Downloads/CA SSI.csv"))
			
		.when()
			.post("/post")
		.then().log().all()
			.assertThat()
				.statusCode(200);
	}

	@Test
	public void bodyWithSingleXLSxFileTest() {
		
		RestAssured.baseURI = "https://postman-echo.com";
		
		given().log().all()
			.contentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
			.body(new File("/Users/v0l00xk/Downloads/DC Mask.xlsx"))
			
		.when()
			.post("/post")
		.then().log().all()
			.assertThat()
				.statusCode(200);
	}
	
	

}
