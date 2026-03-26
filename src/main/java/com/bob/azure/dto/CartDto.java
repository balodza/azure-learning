package com.bob.azure.dto;

import com.bob.azure.entity.jpa.Make;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartDto {
    private int id;
    private String model;
    private int year;
    private String version;
    private int enginePistons;
    private double engineVolume;
    private int enginePower;
    private Make make;
}
