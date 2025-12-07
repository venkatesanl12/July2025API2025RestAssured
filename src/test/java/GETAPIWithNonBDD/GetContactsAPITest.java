package GETAPIWithNonBDD;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetContactsAPITest {
	
	@Test
	public void getContactsTest() {
		
		RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";

		
		RequestSpecification request = RestAssured.given();
		
		request.header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2ODg5ODdlYzU4Mzg2OTAwMTVhNDE4Y2IiLCJpYXQiOjE3NTg1MTAxMzF9.qYv9xIZJmwzn0tD_F4Xiqqs-sMtlcmIrsftyvCdE9Yw");		
		
		Response response = request.get("/contacts");

		int statusCode = response.statusCode();
		String statusLine = response.statusLine();
		
		System.out.println(statusCode);
		System.out.println(statusLine);
		
		//response.prettyPrint();
		
		List<Header> headersList = response.headers().asList();
		System.out.println("total number of headers: "+ headersList.size());
		
		for(Header e : headersList) {
			String headerName = e.getName();
			String headerValue = e.getValue();
			System.out.println(headerName + ":" + headerValue);
		}

}
}
