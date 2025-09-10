package com.dashboard_gk.dashboard_gk.repository;

import com.dashboard_gk.dashboard_gk.model.Concept;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConceptRepository extends MongoRepository<Concept, String> {

}
