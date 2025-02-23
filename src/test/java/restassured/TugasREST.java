package restassured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TugasREST {

    public static void main(String[] args) {
        // listAllObjects();
        // listObjectsId();
        // singleObject();
        addObject();
        // updateObject();
        // partiallyUpdateObject();
        // deleteObject();
                                }
            
        
            public static void listAllObjects(){
        /*
         * baseURI = "https://restful-api.dev/"
         * INI LIST OF ALL OBJECT
         * METHOD : GET
         */
        
        RestAssured.baseURI = "https://api.restful-api.dev/";

        RequestSpecification requestSpecification = RestAssured
                                                    .given();

        Response response = requestSpecification
                            .log()
                            .all()
                        .when()
                            .get("objects");

                            System.out.println("List of all objects : " + response.asPrettyString());
    }

    public static void listObjectsId(){
        /*
         * baseURI = "https://api.restful-api.dev/"
         * API yang dituju https://api.restful-api.dev/objects?id=3
         * INI LIST OBJECT by ID
         * PATH PARAM : idObject
         * ID OBJECT : 3,5,10
         * METHOD : GET
         */
        
        RestAssured.baseURI = "https://api.restful-api.dev/";
        RequestSpecification requestSpecification = RestAssured
                                                    .given();

        int [] idObject = {3,5,10};

        for (int id : idObject) {
            Response response = requestSpecification
                            .log()
                            .all()
                            .queryParam("id", id)
                        .when()
                            .get("objects");
                            
                            if (response.getStatusCode() == 200) {
                                System.out.println("Respons untuk id " + id + " adalah: " + response.asPrettyString());
                            } else {System.out.println("Gagal mendapatkan data. Status Code: " + response.getStatusCode());
                        }
        }
    }

    public static void singleObject(){

        RestAssured.baseURI = "https://api.restful-api.dev/";
        RequestSpecification requestSpecification = RestAssured
                                                    .given();

        Response response = requestSpecification
                            .log()
                            .all()
                            .pathParam("path", "objects")
                            .pathParam("idObject", 7)
                        .when()
                            .get("{path}/{idObject}");
                            
                            if (response.getStatusCode() == 200) {
                                System.out.println("Single Object : " + response.asPrettyString());
                            } else {System.out.println("Gagal mendapatkan data. Status Code: " + response.getStatusCode());
                        }
    }

    public static void addObject(){
        /*
         * baseURI = "https://api.restful-api.dev/"
         * API yang dituju https://api.restful-api.dev/objects
         * INI ADD OBJECT
         * METHOD : POST
         */
        
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
                            .body(json)
                            .contentType(ContentType.JSON)
                        .when()
                            .post("/objects");
                            
                            System.out.println("Add Object : " + response.asPrettyString());
                            System.out.println("Response Body: " + response.asPrettyString());
        
                        }

    public static void updateObject(){
        /*
         * // baseURI = "https://api.restful-api.dev/"
        // API yang dituju https://api.restful-api.dev/objects
        // INI UPDATE OBJECT
        // METHOD : PUT
         */
    
        String json = "{\r\n" + //
                        "    \"name\": \"Samsung S25 Ultra\",\r\n" + //
                        "    \"data\": {\r\n" + //
                        "    \"year\": 2025,\r\n" + //
                        "      \"price\": 1849.99,\r\n" + //
                        "      \"CPU model\": \"Snapdragon 8 Elite\",\r\n" + //
                        "      \"Hard disk size\": \"1 TB\",\r\n" + //
                        "      \"Colour\" : \"Deep Purple\"\r\n" + //
                        "        }\r\n" + //
                        "}";
        
        RestAssured.baseURI = "https://api.restful-api.dev/";
        RequestSpecification requestSpecification = RestAssured
                                                    .given();
                                                
        Response response = requestSpecification
                            .log()
                            .all()
                            .pathParam("path", "objects")
                            .pathParam("id", "ff808181932badb601950e097fa837b9")
                            .body(json)
                            .contentType(ContentType.JSON)
                        .when()
                            .put("{path}/{id}");
                            
                            System.out.println("Update Object : " + response.asPrettyString());
    }

    public static void partiallyUpdateObject(){
        /*
         * // baseURI = "https://api.restful-api.dev/"
        // API yang dituju https://api.restful-api.dev/objects
        // INI UPDATE OBJECT
        // METHOD : PUT
         */
    
        String json = "{\r\n" + //
                        "   \"name\": \"Samsung S25 Ultra Pro Max\"\r\n" + //
                        "}";
        
        RestAssured.baseURI = "https://api.restful-api.dev/";
        RequestSpecification requestSpecification = RestAssured
                                                    .given();
                                                
        Response response = requestSpecification
                            .log()
                            .all()
                            .pathParam("path", "objects")
                            .pathParam("id", "ff808181932badb601950e097fa837b9")
                            .body(json)
                            .contentType(ContentType.JSON)
                        .when()
                            .put("{path}/{id}");
                            
                            System.out.println("Update Object : " + response.asPrettyString());
    }

    public static void deleteObject(){
        /*
         * // baseURI = "https://api.restful-api.dev/"
        // API yang dituju https://api.restful-api.dev/objects
        // INI DELETE OBJECT
        // METHOD : DELETE
         */
    
        RestAssured.baseURI = "https://api.restful-api.dev/";
        RequestSpecification requestSpecification = RestAssured
                                                    .given();
                                                
        Response response = requestSpecification
                            .log()
                            .all()
                            .pathParam("path", "objects")
                            .pathParam("id", "ff808181932badb601950e097fa837b9")
                        .when()
                            .delete("{path}/{id}");
                            
                            System.out.println("Delete Object : " + response.asPrettyString());
    }   


}