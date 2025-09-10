package com.dashboard_gk.dashboard_gk.repository;

import com.dashboard_gk.dashboard_gk.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {

}
