package com.apitest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductDetails {

    @JsonProperty("CPU model")
    private String cpuModel;

    @JsonProperty("Hard disk size")
    private String hardDiskSize;
    private int year;
    private double price;
}
