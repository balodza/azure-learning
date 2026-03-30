package com.bob.azure.dto.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDto {
    private int errorCode;
    private String errorMessage;
}
