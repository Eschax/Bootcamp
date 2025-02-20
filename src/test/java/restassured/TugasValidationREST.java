package restassured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.ResponseObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class TugasValidationREST {

    @Test
    public void testGetAllObjects() {
        RestAssured.baseURI = "https://api.restful-api.dev";

        Response response = RestAssured.given()
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
}