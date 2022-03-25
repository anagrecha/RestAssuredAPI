package restAssuredTests;

import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
//import static org.hamcrest.Matcher.*;
//import static io.restassured.matcher.RestAssuredMatchers.*;


public class GetAndPostExamples {
	
	//@Test
	public void testGet() {
		
		baseURI = "https://reqres.in/api";
	
		given().
			get("/users?page=2").
		then().
			statusCode(200).
			body("data[4].first_name", equalTo("George")).
			log().all();
	}
	
	//@Test
	public void testPost() {
		
		
		JSONObject request = new JSONObject();
		request.put("name", "Aditi");
		request.put("job", "map");
		
		System.out.println(request);
		System.out.println(request.toString());
		
		baseURI = "https://reqres.in/api";
		
		given().
		    //header("Content-Type","application/json").
			contentType(ContentType.JSON).
			//accept(ContentType.JSON).
		    body(request.toJSONString()).
		when().
			post("/users/2").
		then().	
			statusCode(201).
			log().all();
	}
	
	@Test
	
	public void testPut() {
		
		JSONObject request = new JSONObject();
		
		request.put("name", "aditi");
		request.put("job", "request");
		
		baseURI = "https://reqres.in/api";
		
		given().
			contentType(ContentType.JSON).
			body(request.toJSONString()).
		when().
			put("/api/users/2").
		then().	
			statusCode(200).
			log().all();
	
	
	}

}
