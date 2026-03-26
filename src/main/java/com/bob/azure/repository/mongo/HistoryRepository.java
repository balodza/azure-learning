package com.bob.azure.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bob.azure.entity.mongo.History;

@Repository
public interface HistoryRepository extends MongoRepository<History, String> {
}
