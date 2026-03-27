package com.bob.azure.dto;

import lombok.Data;

@Data
public class CreateCarDto {
    private String make;
    private String model;
    private int year;
    private String version;
    private int enginePistons;
    private double engineVolume;
    private int enginePower;
}
