package model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseObject {
    @JsonProperty("id")
    public String id;

    @JsonProperty("name")
    public String name;

    @JsonProperty("createdAt")
    public String createdAt;

    @JsonProperty("data")
    public Data data;


public static class Data {

@JsonIgnoreProperties(ignoreUnknown = true)

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
    @JsonAlias("cpuModel")
    public String cpuModel;

    @JsonProperty("Hard disk size")
    @JsonAlias("hardDiskSize")
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

}