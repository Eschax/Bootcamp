package apiengine;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Endpoints {
    RequestSpecification requestSpecification;

    public Response getAllProducts(String path){
        RestAssured.baseURI = "https://api.restful-api.dev";

        RequestSpecification requestSpecification = RestAssured
                                                    .given();

        Response response = requestSpecification
                                .log()
                                .all()
                                .pathParam("path", "objects")
                            .when()
                                .get("{path}");

        return response;
    }
    
    public Response addProductData(String path, String json){
        RestAssured.baseURI = "https://api.restful-api.dev";
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
    
        return response;
    }

    public Response getProductByID(String Path, String objectId){
        RestAssured.baseURI = "https://api.restful-api.dev";
    RequestSpecification requestSpecification = RestAssured.given();

    Response response = requestSpecification
                            .log()
                            .all()
                            .pathParam("path", Path)
                            .pathParam("idObject", objectId)
                            .contentType("application/json")
                        .when()
                            .get("{path}/{idObject}");

            return response;

    }

}
