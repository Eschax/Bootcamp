package scenario;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.ResponseObject;

public class tugasEndtoEnd {

    private static String objectId;
    ResponseObject ResponseObject;

        /*
         * Scenario Add Product
        * 1. Create new object (hit API add_object)
        * 2. verify new object is added (hit API single_object)
        * 3. Delete product (hit API delete_object)
        * 4. Verify new object is deleted (hit API single_object)
         */

    @Test
    public void EndtoEnd() {
        System.out.println("Add Product");

        
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

        Response response = requestSpecification
                            .log()
                            .all()
                            .pathParam("path", "objects")
                            .body(json)
                            .contentType("application/json")
                        .when()
                            .post("{path}");

                JsonPath jsonPath = response.jsonPath();
                objectId = jsonPath.getString("id");

                ResponseObject responseObject = jsonPath.getObject("", ResponseObject.class);
                            
                Assert.assertEquals(responseObject.name, "Samsung S25 Ultra");
                Assert.assertNotNull(responseObject.createdAt);
                Assert.assertEquals(responseObject.data.year, 2025);
                Assert.assertEquals(responseObject.data.price, 2000);
                Assert.assertEquals(responseObject.data.cpuModel, "Snapdragon 8 Elite");
                            
                System.out.println("Response: " + response.asPrettyString());
                System.out.println("ID: " + objectId);

                String idObject = responseObject.id;

                //get product
                System.out.println("Get Product");

        Response response2 = requestSpecification
                            .log()
                            .all()
                            .pathParam("path", "objects")
                            .pathParam("idObject", idObject)
                        .when()
                            .get("{path}/{idObject}");

                        objectId = jsonPath.getString("id");
            
                            Assert.assertEquals(responseObject.name, "Samsung S25 Ultra");
                            Assert.assertNotNull(responseObject.createdAt);
                            Assert.assertEquals(responseObject.data.year, 2025);
                            Assert.assertEquals(responseObject.data.price, 2000);
                            Assert.assertEquals(responseObject.data.cpuModel, "Snapdragon 8 Elite");
                                        
                            System.out.println("Response2: " + response2.asPrettyString());
                            System.out.println("ID: " + objectId);

                            //delete product
                            System.out.println("Delete Product");
            Response response3 = requestSpecification
                            .log()
                            .all()
                            .pathParam("path", "objects")
                            .pathParam("idObject", idObject)
                        .when()
                            .delete("{path}/{idObject}");

                            Assert.assertEquals(response3.getStatusCode(), 200);
                            System.out.println("Response3: " + response3.asPrettyString());
                            System.out.println("ID: " + objectId);

                            //verify deleted product
                            System.out.println("Verify Deleted Product");
            
            Response response4 = requestSpecification
                            .log()
                            .all()
                            .pathParam("path", "objects")
                            .pathParam("idObject", idObject)
                        .when()
                            .get("{path}/{idObject}");

                            Assert.assertEquals(response4.getStatusCode(), 404);
                            System.out.println("Response4: " + response4.asPrettyString());
                            System.out.println("ID: " + objectId);
    }
}
