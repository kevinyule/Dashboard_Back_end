package com.dashboard_gk.dashboard_gk.controller;

import com.dashboard_gk.dashboard_gk.dto.UserRequestDTO;
import com.dashboard_gk.dashboard_gk.dto.UserResponseDTO;
import com.dashboard_gk.dashboard_gk.interfaces.IUserService;
import com.dashboard_gk.dashboard_gk.response.ObjectResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard-gk-backend/user-controller")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    IUserService userService;

    @PostMapping("/create-user")
    public ResponseEntity<ObjectResponse> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO){

        ObjectResponse response = new ObjectResponse();
        response.setStatusCode(0);
        response.setMessage("User created successfully");
        try {
            LOG.info("Creating user: {}", userRequestDTO);
            UserResponseDTO userResponseDTO = userService.createUser(userRequestDTO);
            response.setObject(userResponseDTO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setStatusCode(-1);
            response.setMessage("Failed to create user");
            LOG.error("Error creating user: {}", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
