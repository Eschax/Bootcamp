package stepdefinitions;


import java.util.Map;
import org.testng.Assert;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.DataRequest;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tugasqa.model.ResponseObject;
import com.tugasqa.model.request.RequestItem;


public class StepDefinitions {

    String objectId;
    ResponseObject ResponseObject;
    RequestItem requestItem;
    DataRequest DataRequest; 
    String json;
    String idProduct;

    // Inisialisasi DataRequest di constructor
    public StepDefinitions() {
        this.DataRequest = new DataRequest(); 
    }

    @Given("A list of item are available")
    public void AllProducts() {
        System.out.println("List produk");
        RestAssured.baseURI = "https://api.restful-api.dev";

        RequestSpecification requestSpecification = RestAssured
                                                    .given();

        Response response = requestSpecification
                                .log()
                                .all()
                                .pathParam("path", "objects")
                            .when()
                                .get("{path}");

        System.out.println("Response: " + response.asPrettyString());
    }

    @When("I add item to list {string}")
    public void addItem(String payload) throws JsonProcessingException, com.fasterxml.jackson.core.JsonProcessingException {
    System.out.println("ini when pake parameter");

    RestAssured.baseURI = "https://api.restful-api.dev";
    RequestSpecification requestSpecification = RestAssured
                                                .given();

        for(Map.Entry<String, String> entry : DataRequest.addItemColletion().entrySet()){
            if (entry.getKey().equals(payload)) {
                json = entry.getValue();
                break;
            }
        }
        Response response = requestSpecification
        .log()
        .all()
        .pathParam("path", "objects")
        .body(json)
        .contentType("application/json")
    .when()
        .post("{path}");

        ObjectMapper requestAddItem = new ObjectMapper();
        requestItem = requestAddItem.readValue(json, RequestItem.class);

    // Ambil ID dari respons
    JsonPath addJsonPath = response.jsonPath();
    objectId = addJsonPath.getString("id"); 
    
    // Validasi respons
    ResponseObject = addJsonPath.getObject("", ResponseObject.class);
    
    // Assertion
    Assert.assertEquals(response.getStatusCode(), 200, "Status code");
    Assert.assertEquals(ResponseObject.name, requestItem.name);
    Assert.assertEquals(ResponseObject.data.year, requestItem.data.year);
    Assert.assertEquals(ResponseObject.data.price, requestItem.data.price);
    Assert.assertEquals(ResponseObject.data.cpuModel, requestItem.data.cpuModel);

}

    @When("I add item to list")
    public void addItem() throws JsonProcessingException, JsonMappingException, com.fasterxml.jackson.core.JsonProcessingException {
    RestAssured.baseURI = "https://api.restful-api.dev";
    System.out.println("ini When tanpa parameter");
    RequestSpecification requestSpecification = RestAssured.given();

    // Ambil payload default
    json = DataRequest.addItemColletion().values().iterator().next();

    Response response = requestSpecification
        .log()
        .all()
        .pathParam("path", "objects")
        .body(json)
        .contentType("application/json")
        .when()
        .post("{path}");

    ObjectMapper requestAddItem = new ObjectMapper();
    requestItem = requestAddItem.readValue(json, RequestItem.class);

    // Ambil ID dari respons
    JsonPath addJsonPath = response.jsonPath();
    objectId = addJsonPath.getString("id");

    // Validasi respons
    ResponseObject = addJsonPath.getObject("", ResponseObject.class);

    // Validasi data
    Assert.assertEquals(response.getStatusCode(), 200, "Status code");
    Assert.assertEquals(ResponseObject.name, requestItem.name);
    Assert.assertEquals(ResponseObject.data.year, requestItem.data.year);
    Assert.assertEquals(ResponseObject.data.price, requestItem.data.price);
    Assert.assertEquals(ResponseObject.data.cpuModel, requestItem.data.cpuModel);
}

    @Then("The item is available")
    public void the_item_is_available() throws JsonProcessingException {
    System.out.println("ini then tanpa parameter");

    RestAssured.baseURI = "https://api.restful-api.dev";
    RequestSpecification requestSpecification = RestAssured.given();

    Response response = requestSpecification
        .log()
        .all()
        .pathParam("path", "objects")
        .pathParam("idObject", objectId)
        .contentType("application/json")
        .when()
        .get("{path}/{idObject}");

    JsonPath jsonPath = response.jsonPath();
    ResponseObject responseObject = jsonPath.getObject("", ResponseObject.class);

    Assert.assertEquals(responseObject.name, requestItem.name);
    Assert.assertEquals(responseObject.data.year, requestItem.data.year);
    Assert.assertEquals(responseObject.data.price, requestItem.data.price);
    Assert.assertEquals(responseObject.data.cpuModel, requestItem.data.cpuModel);
    }

}