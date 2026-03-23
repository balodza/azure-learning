package com.bob.azure.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GroupDto {
    private int id;
    private String groupName;
}
