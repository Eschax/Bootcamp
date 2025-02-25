package resources;

import java.util.HashMap;
import java.util.Map;

public class DataRequest {
    public Map<String, String> addItemColletion() {
        Map<String, String> dataCollection = new HashMap<>();
        
        dataCollection.put("addItem", 
            "{ \n" +
            "   \"name\": \"Samsung S25 Ultra\",\n" +
            "   \"data\": {\n" +
            "      \"year\": 2025,\n" +
            "      \"price\": 2000,\n" +
            "      \"cpuModel\": \"Snapdragon 8 Elite\",\n" +
            "      \"hardDiskSize\": \"512 GB\"\n" +
            "   }\n" +
            "}"
        );
        dataCollection.put("addItem2", 
            "{ \n" +
            "   \"name\": \"Samsung S24 Ultra\",\n" +
            "   \"data\": {\n" +
            "      \"year\": 2024,\n" +
            "      \"price\": 1900,\n" +
            "      \"cpuModel\": \"Snapdragon 888\",\n" +
            "      \"hardDiskSize\": \"1 TB\"\n" +
            "   }\n" +
            "}"
        );
        
        return dataCollection;
    }
}