package restassured;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ValidationTest {

    @Test
    public void createProduct(){
        String json = "{\r\n" + //
                        "    \"title\": \"Annibale Colombo Bed\",\r\n" + //
                        "    \"price\": 29.99,\r\n" + //
                        "    \"description\": \"Annibale Colombo Bed is a popular mascara known for its volumizing and lengthening effects. Achieve dramatic lashes with this long-lasting and cruelty-free formula.\"\r\n" + //
                        "}";


        RestAssured.baseURI = "https://dummyjson.com";
                RequestSpecification requestSpecification = RestAssured
                                                            .given();
    /*
     * POST harus masukin body request
     * masukin di setup dalam bentuk JSON
     */
                Response response = requestSpecification
                                        .log()
                                        .all()
                                        .pathParam("path", "products")
                                        .pathParam("method", "add")
                                        .body(json)
                                        .contentType("application/json")
                                        .when()
                                            .post("{path}/{method}");

                                        System.out.println("add product : " + response.asPrettyString());
    }

    @Test
    public void createObject(){
        String json = "{\r\n" + 
                "   \"name\": \"Samsung S25 Ultra\",\r\n" + 
                "   \"data\": {\r\n" + 
                "      \"year\": 2025,\r\n" + 
                "      \"price\": 1849.99,\r\n" + 
                "      \"cpuModel\": \"Snapdragon 8 Elite\",\r\n" + 
                "      \"hardDiskSize\": \"1 TB\"\r\n" + 
                "   }\r\n" + 
                "}";

        
        RestAssured.baseURI = "https://api.restful-api.dev/";
        RequestSpecification requestSpecification = RestAssured
                                                    .given();

        Response response = requestSpecification
                            .log()
                            .all()
                            .pathParam("path", "objects")
                            .body(json)
                            .contentType("application/json")
                        .when()
                            .post("{path}");
                            
                            System.out.println("Add Object : " + response.asPrettyString());
                            System.out.println("Response Body: " + response.asPrettyString());

            System.out.println("resoponse API" + response.asPrettyString());
    }

}
