package restassured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredlmpl {
    public static void main(String[] args) {
        // getAllproduct();
        // getSingleProduct();
        // searchProduct();
        // addProduct();
        // updateProduct();
        deleteProduct();
            }
        
            public static void getAllproduct(){
                //define baseURI
                /*
                 * "https://dummyjson.com/products"
                 * baseURI = "https://dummyjson.com"
                 * path = "/products"
                 */
        
                RestAssured.baseURI = "https://dummyjson.com";
                RequestSpecification requestSpecification = RestAssured //ini biar gak ulang-ulang pemanggilan RestAssured .given()
                                                            .given(); // untuk setup param juga
        
                Response response = requestSpecification.log().all()
                                    .get("products"); //cara pertama
        
                Response response2 = RestAssured //cara kedua
                                    .given()
                                        .log()
                                        .all()
                                    .when()
                                        .get("products");
        
                System.out.println("Hasilnya adalah : " + response2.asPrettyString());
                System.out.println("Hasilnya adalah : " + response.asPrettyString());
            }   
        
            public static void getSingleProduct(){
                //define baseURI
                /*
                 * "https://dummyjson.com/products/1" /1 itu adalh path param
                 */
        
                RestAssured.baseURI = "https://dummyjson.com";
                RequestSpecification requestSpecification = RestAssured
                                                            .given();
        
                Response response = requestSpecification
                                        .log()
                                        .all()
                                        .pathParam("idProduct", 1)
                                        .pathParam("path", "products")
                                    .when()
                                        .get("{path}/{idProduct}");
                                        
                System.out.println("Single Product : " + response.asPrettyString());
            }
        
            public static void searchProduct(){
        /*
         * https://dummyjson.com/products/search?q=phone
         */
        RestAssured.baseURI = "https://dummyjson.com";
        RequestSpecification requestSpecification = RestAssured
                                                    .given();

        Response response = requestSpecification
                                                    .log()
                                                    .all()
                                                    .pathParam("path", "products")
                                                    .pathParam("method", "search")     
                                                    .queryParam("q", "iPhone 5s")
                                                    .when()
                                                        .get("{path}/{method}");   
                                                        
        System.out.println("ini adalah hasil Search : " + response.asPrettyString());
    }

    public static void addProduct(){

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

    public static void updateProduct(){

        String json = "{\r\n" + //
                        "    \"title\": \"Annibale combo super\",\r\n" + //
                        "    \"price\": 29.99,\r\n" + //
                        "    \"description\": \"Annibale Colombo Bed is a popular mascara known for its volumizing and lengthening effects. Achieve dramatic lashes with this long-lasting and cruelty-free formula.\"\r\n" + //
                        "}";

        RestAssured.baseURI = "https://dummyjson.com";
                RequestSpecification requestSpecification = RestAssured
                                                            .given();

                Response response = requestSpecification
                                    .log()
                                    .all()
                                    .pathParam("path", "products")
                                    .pathParam("idProduct", "1")
                                    .body(json)
                                    .contentType("application/json")
                                    .when()
                                        .put("{path}/{idProduct}");

                                        System.out.println("update product : " + response.asPrettyString());
    }

    public static void deleteProduct(){

        RestAssured.baseURI = "https://dummyjson.com";
                RequestSpecification requestSpecification = RestAssured
                                                            .given();

                Response response = requestSpecification
                                    .log()
                                    .all()
                                    .pathParam("path", "products")
                                    .pathParam("idProduct", "1")
                                    .when()
                                        .delete("{path}/{idProduct}");

                                        System.out.println("delete product : " + response.asPrettyString());
    }
}

