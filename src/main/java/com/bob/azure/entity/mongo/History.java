package com.bob.azure.entity.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "history")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class History {
    @Id
    private String id;
    
    private String payload;
}
