package com.bob.azure.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Group {
    private String id;
    private String name;
}
