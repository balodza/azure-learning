package com.bob.azure.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MakeDto {
    private int id;
    private String name;
}
