package com.bob.azure.dto;

import com.bob.azure.validation.ValidCarMake;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CreateCarDto {
    @NotBlank
    @ValidCarMake
    private String make;
    @NotBlank
    private String model;
    @Min(1886)
    private int year;
    @NotBlank
    private String version;
    @Positive
    private int enginePistons;
    @Positive
    private double engineVolume;
    @Positive
    private int enginePower;
}
