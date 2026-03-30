package com.bob.azure.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DeleteCarDto {
    @NotBlank
    private String make;
    @NotBlank
    private String model;
}
