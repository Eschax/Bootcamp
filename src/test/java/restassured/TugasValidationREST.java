package restassured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.ResponseObject;
import org.testng.Assert;
import org.testng.annotations.Test;



import java.util.List;

public class TugasValidationREST {

    private static String objectId;

    @Test
    public void GetAllObjects() {
        RestAssured.baseURI = "https://api.restful-api.dev";

        RequestSpecification requestSpecification = RestAssured
                                                    .given();

        Response response = requestSpecification
                .pathParam("path", "objects")
                .when()
                .get("{path}");

        List<ResponseObject> products = response.jsonPath().getList("", ResponseObject.class);

        Assert.assertFalse(products.isEmpty(), "List produk");
        System.out.println("Jumlah produk: " + products.size());
        for (ResponseObject product : products) {
            System.out.println("ID: " + product.id);
            System.out.println("Nama: " + product.name);
            if (product.data != null) {
                System.out.println("Data: " + product.data);
            }
            System.out.println("-----------------------------");
        }
    
    }

    @Test
    public void listObjectID() {
        RestAssured.baseURI = "https://api.restful-api.dev";
    
        RequestSpecification requestSpecification = RestAssured.given();
    
        int[] idObject = {3, 5, 10};
    
        for (int id : idObject) {
            Response response = requestSpecification
                    .log()
                    .all()
                    .pathParam("path", "objects")
                    .pathParam("id", id)
                    .when()
                    .get("{path}/{id}");
    

                ResponseObject product = response.as(ResponseObject.class);
    
                Assert.assertNotNull(product, "Kok gaada cok?");
                System.out.println("ID: " + product.id);
                System.out.println("Nama: " + product.name);
    
                System.out.println("-----------------------------");
            
        }
    }
    

    @Test
    public void singleObject() {
        RestAssured.baseURI = "https://api.restful-api.dev";

        RequestSpecification requestSpecification = RestAssured
                                                    .given();

        Response response = requestSpecification
                            .log()
                            .all()
                            .pathParam("path", "objects")
                            .pathParam("idObject", 7)
                        .when()
                            .get("{path}/{idObject}");

        ResponseObject product = response.as(ResponseObject.class);

        Assert.assertNotNull(product, "Produk");
        Assert.assertNotNull(product.id, "7");
        Assert.assertEquals(product.name, "Apple MacBook Pro 16");
        Assert.assertEquals(product.data.year, 2019);
        Assert.assertEquals(product.data.price, 1849.99);
        Assert.assertEquals(product.data.cpuModel, "Intel Core i9");

    }
    @Test
    // @BeforeMethod
    public void addObject() {

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
                Assert.assertEquals(responseObject.data.year, 2025);
                Assert.assertEquals(responseObject.data.price, 2000);
                Assert.assertEquals(responseObject.data.cpuModel, "Snapdragon 8 Elite");
                            
                System.out.println("Response: " + response.asPrettyString());
                System.out.println("ID: " + objectId);

                }     

    @Test(dependsOnMethods = "addObject")
    public void updateObject() {

        Assert.assertNotNull(objectId, "MUNCUL GAN!!!");
    
        String json = "{\r\n" + 
                "   \"name\": \"Samsung S25 Ultra\",\r\n" + 
                "   \"data\": {\r\n" + 
                "      \"year\": 2025,\r\n" + 
                "      \"price\": 2500,\r\n" + 
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
                            .pathParam("idObject", objectId)
                            .body(json)
                            .contentType("application/json")
                        .when()
                            .put("{path}/{idObject}");

                JsonPath jsonPath = response.jsonPath();
                ResponseObject responseObject = jsonPath.getObject("", ResponseObject.class);

        Assert.assertEquals(response.getStatusCode(), 200, "Status code");
        Assert.assertEquals(responseObject.name, "Samsung S25 Ultra");
        Assert.assertEquals(responseObject.data.year, 2025);
        Assert.assertEquals(responseObject.data.price, 2500);
        Assert.assertEquals(responseObject.data.cpuModel, "Snapdragon 8 Elite");

        System.out.println("Response: " + response.asPrettyString());
    }

    @Test(dependsOnMethods = "updateObject")
    public void partiallyUpdate(){
        String json = "{\r\n" + 
                "   \"name\": \"Samsung S25 Ultra\",\r\n" + 
                "   \"data\": {\r\n" + 
                "      \"year\": 2025,\r\n" + 
                "      \"price\": 2700,\r\n" + 
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
                            .pathParam("idObject", objectId)
                            .body(json)
                            .contentType("application/json")
                        .when()
                            .patch("{path}/{idObject}");

                            JsonPath jsonPath = response.jsonPath();
                            ResponseObject responseObject = jsonPath.getObject("", ResponseObject.class);
            
                    Assert.assertEquals(response.getStatusCode(), 200, "Status code");
                    Assert.assertEquals(responseObject.name, "Samsung S25 Ultra");
                    Assert.assertEquals(responseObject.data.year, 2025);
                    Assert.assertEquals(responseObject.data.price, 2700);
                    Assert.assertEquals(responseObject.data.cpuModel, "Snapdragon 8 Elite");
            
                    System.out.println("Response: " + response.asPrettyString());
                }

    @Test(dependsOnMethods = "partiallyUpdate")
    public void deleteObject() {
        RestAssured.baseURI = "https://api.restful-api.dev";

        RequestSpecification requestSpecification = RestAssured
                                                    .given();

        Response response = requestSpecification
                            .log()
                            .all()
                            .pathParam("path", "objects")
                            .pathParam("idObject", objectId)
                        .when()
                            .delete("{path}/{idObject}");

        Assert.assertEquals(response.getStatusCode(), 200, "Status code");
        System.out.println("Response: " + response.asPrettyString());
    }
}

