package model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseObject {
    @JsonProperty("id")
    public String id;

    @JsonProperty("name")
    public String name;

    @JsonProperty("data")
    public ProductData data;
}

class ProductData {
    @JsonProperty("color")
    @JsonAlias("Color")
    public String color;

    @JsonProperty("capacity")
    public String capacity;

    @JsonProperty("capacity GB")
    public Integer capacityGB;

    @JsonProperty("price")
    public Double price;

    @JsonProperty("generation")
    public String generation;

    @JsonProperty("year")
    public Integer year;

    @JsonProperty("CPU model")
    public String cpuModel;

    @JsonProperty("Hard disk size")
    public String hardDiskSize;

    @JsonProperty("Strap Colour")
    public String strapColour;

    @JsonProperty("Case Size")
    public String caseSize;

    @JsonProperty("Description")
    public String description;

    @JsonProperty("Screen size")
    public Double screenSize;

    @JsonProperty("Generation")
    public String generationCapitalized;

    @JsonProperty("Price")
    public String priceCapitalized;

    @JsonProperty("Capacity")
    public String capacityCapitalized;
}