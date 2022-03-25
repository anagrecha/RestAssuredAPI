package actualTest;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class SOAPXMLRequest {
			
	@Test
	public void vadilateSOAPXML() throws IOException {
		
		File file = new File("./SoapRequest/Add.xml");
		
		if (file.exists())
			System.out.println(">> File Exists <<");
		
		FileInputStream fileIO = new FileInputStream(file);
		
		String requestBody = IOUtils.toString(fileIO, "utf-8");
		
		baseURI = "http://www.dneonline.com";
		
		given().
			contentType("text/xml").
			accept(ContentType.XML).
			body(requestBody).
		when().	
			post("/calculator.asmx").
		then().
			statusCode(200).
			log().all().
		and().
			body("//*:AddResult.text()", equalTo("5"));
			
	}

}
