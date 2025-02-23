package stepdefinitions;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.ResponseObject;

public class StepDefinitions {

    private static String objectId;

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

    @When("I add item to list")
    public void itemisAdded() {
        System.out.println("Menambahkan produk");
        String json = "{\r\n" + 
        "   \"name\": \"Samsung S25 Ultra\",\r\n" + 
        "   \"data\": {\r\n" + 
        "      \"year\": 2025,\r\n" + 
        "      \"price\": 2000,\r\n" + 
        "      \"cpuModel\": \"Snapdragon 8 Elite\",\r\n" + 
        "      \"hardDiskSize\": \"1 TB\"\r\n" + 
        "   }\r\n" + 
        "}";

RestAssured.baseURI = "https://api.restful-api.dev";

RequestSpecification requestSpecification = RestAssured
                                            .given();

            Response response2 = requestSpecification
                    .log()
                    .all()
                    .pathParam("path", "objects")
                    .body(json)
                    .contentType("application/json")
                .when()
                    .post("{path}");

                JsonPath jsonPath = response2.jsonPath();

                objectId = jsonPath.getString("id");

                ResponseObject responseObject = jsonPath.getObject("", ResponseObject.class);
                            
                Assert.assertEquals(responseObject.name, "Samsung S25 Ultra");
                Assert.assertEquals(responseObject.data.year, 2025);
                Assert.assertEquals(responseObject.data.price, 2000);
                Assert.assertEquals(responseObject.data.cpuModel, "Snapdragon 8 Elite");
                            
                System.out.println("Response: " + response2.asPrettyString());
                System.out.println("ID: " + objectId);

                }     

    @Then("The item is available")
    public void itemIsAvailable() {
        System.out.println("Produk tersedia");

        RestAssured.baseURI = "https://api.restful-api.dev";

        RequestSpecification requestSpecification = RestAssured
                                                    .given();

        Response response3 = requestSpecification
                            .log()
                            .all()
                            .pathParam("path", "objects")
                            .pathParam("idObject", objectId)
                        .when()
                            .get("{path}/{idObject}");

                            JsonPath jsonPath = response3.jsonPath();
                            ResponseObject responseObject = jsonPath.getObject("", ResponseObject.class);

                            Assert.assertEquals(responseObject.name, "Samsung S25 Ultra");
                            Assert.assertEquals(responseObject.data.year, 2025);
                            Assert.assertEquals(responseObject.data.price, 2000);
                            Assert.assertEquals(responseObject.data.cpuModel, "Snapdragon 8 Elite");
                    
    }
}
