package com.bob.azure.dto;

import com.bob.azure.validation.ValidCarMake;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DeleteCarDto {
    @NotBlank
    @ValidCarMake
    private String make;
    @NotBlank
    private String model;
}
