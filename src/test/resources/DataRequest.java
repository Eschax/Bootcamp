import java.util.HashMap;
import java.util.Map;

public class DataRequest {

    public Map<String, String> AddProduct(){
        Map<String, String> ProductIs = new HashMap<>();

        dataProduct.put("{\r\n" + 
        "   \"name\": \"Samsung S25 Ultra\",\r\n" + 
        "   \"data\": {\r\n" + 
        "      \"year\": 2025,\r\n" + 
        "      \"price\": 2000,\r\n" + 
        "      \"cpuModel\": \"Snapdragon 8 Elite\",\r\n" + 
        "      \"hardDiskSize\": \"1 TB\"\r\n" + 
        "   }\r\n" + 
        "}");
    };

    public Map<String, String> updateProduct(){
        Map<String, String> updateProduct = new HashMap<>();

        updateProduct.put("\"{\\r\\n" + //
                        "\" + \r\n" + //
                        "                \"   \\\"name\\\": \\\"Samsung S25 Ultra\\\",\\r\\n" + //
                        "\" + \r\n" + //
                        "                \"   \\\"data\\\": {\\r\\n" + //
                        "\" + \r\n" + //
                        "                \"      \\\"year\\\": 2025,\\r\\n" + //
                        "\" + \r\n" + //
                        "                \"      \\\"price\\\": 2500,\\r\\n" + //
                        "\" + \r\n" + //
                        "                \"      \\\"cpuModel\\\": \\\"Snapdragon 8 Elite\\\",\\r\\n" + //
                        "\" + \r\n" + //
                        "                \"      \\\"hardDiskSize\\\": \\\"1 TB\\\"\\r\\n" + //
                        "\" + \r\n" + //
                        "                \"   }\\r\\n" + //
                        "\" + \r\n" + //
                        "                \"}\"");
                return updateProduct;
    }
}
