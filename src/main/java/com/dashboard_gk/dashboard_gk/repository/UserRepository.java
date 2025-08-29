package com.dashboard_gk.dashboard_gk.repository;

import com.dashboard_gk.dashboard_gk.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
