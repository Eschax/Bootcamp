package stepdefinitions;


import java.util.Map;
import org.testng.Assert;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.BeforeStep;
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

import apiengine.Assertion;
import apiengine.Endpoints;



public class StepDefinitions {

    String objectId;
    ResponseObject ResponseObject;
    RequestItem requestItem;
    DataRequest DataRequest; 
    String json;
    Response response;
    Endpoints endpoints;
    Assertion assertion;

    @BeforeStep
    public void setUp(){
        endpoints = new Endpoints();
        assertion = new Assertion();
    }

    // Inisialisasi DataRequest di constructor
    public StepDefinitions() {
        this.DataRequest = new DataRequest(); 
    }

    @Given("A list of item are available")
    public void AllProducts() {
        System.out.println("List produk");
        // RE-FACTORY
        response = endpoints.getAllProducts("objects");

        System.out.println("Response: " + response.asPrettyString());
    }

    @When("I add item to list {string}")
    public void addItem(String payload) throws JsonProcessingException, com.fasterxml.jackson.core.JsonProcessingException {
    System.out.println("ini when pake parameter");

            for(Map.Entry<String, String> entry : DataRequest.addItemColletion().entrySet()){
            if (entry.getKey().equals(payload)) {
                json = entry.getValue();
                break;
            }
        }
        // RE-FACTORY
        response = endpoints.addProductData("objects", json);

                //Object mapper
        /*
         * Convert JSON to POJO
         */
        ObjectMapper requestAddItem = new ObjectMapper();
        requestItem = requestAddItem.readValue(json, RequestItem.class);

    // Ambil ID dari respons
    JsonPath addJsonPath = response.jsonPath();
    objectId = addJsonPath.getString("id"); 
    
    // Validasi respons
    ResponseObject = addJsonPath.getObject("", ResponseObject.class);
    
    // Assertion
    Assert.assertEquals(response.getStatusCode(), 200, "Status code");
    assertion.assertAddProduct(ResponseObject, requestItem);

}

    @When("I add item to list")
    public void addItem() throws JsonProcessingException, JsonMappingException, com.fasterxml.jackson.core.JsonProcessingException {

    System.out.println("ini When tanpa parameter");


    // Ambil payload default
    json = DataRequest.addItemColletion().values().iterator().next();

    // RE-FACTORY
    response = endpoints.addProductData("objects", json);

    ObjectMapper requestAddItem = new ObjectMapper();
    requestItem = requestAddItem.readValue(json, RequestItem.class);

    // Ambil ID dari respons
    JsonPath addJsonPath = response.jsonPath();
    objectId = addJsonPath.getString("id");

    // Validasi respons
    ResponseObject = addJsonPath.getObject("", ResponseObject.class);

    // Validasi data
    Assert.assertEquals(response.getStatusCode(), 200, "Status code");
    assertion.assertAddProduct(ResponseObject, requestItem);
}

    @Then("The item is available")
    public void the_item_is_available() throws JsonProcessingException {
    System.out.println("ini then tanpa parameter");

    // RE-FACTORY
    response = endpoints.getProductByID("objects", objectId);

    JsonPath jsonPath = response.jsonPath();
    ResponseObject responseObject = jsonPath.getObject("", ResponseObject.class);

        //RE-Factor
    assertion.the_item_is_available(ResponseObject, requestItem);
    }
}