package com.bob.azure.repository.cosmos;

import org.springframework.stereotype.Repository;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.bob.azure.entity.cosmos.CosmosHistory;

@Repository
public interface CosmosHistoryRepository extends CosmosRepository<CosmosHistory, String> {
}
