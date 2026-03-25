package com.bob.azure.dto;

import com.bob.azure.entity.Make;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartDto {
    private Integer id;
    private String model;
    private int year;
    private String version;
    private int engine_pistons;
    private double engine_volume;
    private int engine_power;
    private Make make;
}
