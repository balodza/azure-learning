package com.bob.azure.entity.cosmos;

import org.springframework.data.annotation.Id;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Container(containerName = "history")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class CosmosHistory {

    @Id
    private String id;

    private String payload;
}
