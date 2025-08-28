package com.dashboard_gk.dashboard_gk.repository;

import com.dashboard_gk.dashboard_gk.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}
