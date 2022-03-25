package actualTest;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsdInClasspath;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

public class XMLSchemaValidation {

	@Test
	public void schemaValidation() throws IOException {
		
		File file = new File (".//SoapRequest/add.xml");
		
		if(file.exists())
				System.out.println("<<File Exists>>");
		
		FileInputStream fileIO = new FileInputStream(file);
		
		String requestBody = IOUtils.toString(fileIO, "UTF-8");
		
		baseURI = "http://www.dneonline.com/";
		given()
			.contentType("text/xml")
			.body(requestBody)
		.when()
			.post("/calculator.asmx")
		.then()
			.statusCode(200)
			.log().all()
			.body("//*:AddResult.text()", equalTo("5"))
			.assertThat().body(matchesXsdInClasspath("calculator.xsd"));
		
	}
	
}
