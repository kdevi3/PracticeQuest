package stepDefinition;

import static io.restassured.RestAssured.given;

import org.junit.Assert;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class GetNaceDetail{
		

	private static String HOST="localhost";
	private  static int PORT;
	private static String END_POINT="/GetNaceDetail";
	private static Response res;
	private WireMockServer wireMockServer;


@Given("^wiremock server is started and Json reponse is mocked$")
public void wiremock_server_is_started_and_Json_reponse_is_mocked() throws Throwable {
	wireMockServer = new WireMockServer(WireMockConfiguration.options().dynamicPort());
	if (!wireMockServer.isRunning()) {
		wireMockServer.start();
	  }
	PORT=wireMockServer.port();
	WireMock.configureFor(HOST,PORT);
	ResponseDefinitionBuilder mockResponse=new ResponseDefinitionBuilder();
	System.out.println("Reading json response file"); 
	WireMock.stubFor(WireMock.get(WireMock.anyUrl())
	        .withQueryParam("order", WireMock.equalTo("398481"))
	        .willReturn(mockResponse.withBodyFile("json/NaceDetail.json")
	        .withHeader("Content-Type", "application/json")));
}

@When("^user send a get request to the URL to get Nace details with order$")
public void user_send_a_get_request_to_the_URL_to_get_Nace_details_with_order() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
	System.out.println("Starting test.......");
	String  testAPI="http://localhost:"+ PORT+ END_POINT;
	res=given().accept(ContentType.JSON).queryParam("order", "398481").log().all().when().get(testAPI);
	System.out.println( res.asString());
	
}

@Then("^validate the status code$")
public void validate_the_status_code() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
     Assert.assertEquals(200,  res.getStatusCode());
     Assert.assertEquals("application/json",res.contentType());
    
    //res.then().body("order", equalTo("398481"));
	
}


@Then("^validate the response include the following \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\\\"]*)\", \"([^\"]*)\"$")
public void validate_the_response_include_the_following(String order, String level, String code, String parent, String description, String includes, String alsoIncludes, String rulings,String itemExcludes, String referenceToISICRev) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    Assert.assertEquals("Order number doesn't match with expected", order, res.jsonPath().get("order"));
	Assert.assertEquals("Actual order_level value doesn't with expected value. ", level, res.jsonPath().get("detail.Level"));
    Assert.assertEquals("Actual order_Code value not matching with expected value. ", code, res.jsonPath().get("detail.Code"));
    Assert.assertEquals("Actual order_Parent value not matching with expected value. ", parent, res.jsonPath().get("detail.Parent"));
    Assert.assertEquals("Actual order_Description value not matching with expected value. ", description, res.jsonPath().get("detail.Description"));
    Assert.assertEquals("Actual order_This_Item_Includes value not matching with expected value. ", includes, res.jsonPath().get("detail.ThisItemIncludes"));
    Assert.assertEquals("Actual order_This_Item_also_Includes value not matching with expected value. ", alsoIncludes, res.jsonPath().get("detail.ThisItemAlsoIncludes"));
    Assert.assertEquals("Actual order_Rulings value not matching with expected value. ", rulings, res.jsonPath().get("detail.Rulings"));
    Assert.assertEquals("Actual order_This_Item_Excludes value not matching with expected value. ", itemExcludes, res.jsonPath().get("detail.ThisItemExcludes"));
    Assert.assertEquals("Actual order_Reference_To_ISIC_Rev._4 value not matching with expected value. ", referenceToISICRev, res.jsonPath().get("detail.ReferenceToISIC_Rev_4"));

}


@Then("^shutdown the wiremock server$")
public void shutdown_the_wiremock_server() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
	wireMockServer.shutdown();
	System.out.println("session ended");
}


}
