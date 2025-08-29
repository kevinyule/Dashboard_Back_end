package com.dashboard_gk.dashboard_gk.controller;

import com.dashboard_gk.dashboard_gk.dto.user.CreateUserRequestDTO;
import com.dashboard_gk.dashboard_gk.dto.user.LoginRequestDTO;
import com.dashboard_gk.dashboard_gk.dto.user.UserResponseDTO;
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
    public ResponseEntity<ObjectResponse> createUser(@Valid @RequestBody CreateUserRequestDTO createUserRequestDTO){

        ObjectResponse response = new ObjectResponse();
        response.setStatusCode(0);
        response.setMessage("Usuario creado exitosamente");
        try {
            LOG.info("Creando Usuario: {}", createUserRequestDTO);
            UserResponseDTO userResponseDTO = userService.createUser(createUserRequestDTO);
            response.setObject(userResponseDTO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setStatusCode(-1);
            response.setMessage("Error al crear usuario: " + e.getMessage());
            LOG.error("\"Error al crear usuario: {}", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }



    @PostMapping("/is-user-valid")
    public ResponseEntity<ObjectResponse> isUserValid(@Valid @RequestBody LoginRequestDTO loginRequestDTO){

        ObjectResponse response = new ObjectResponse();
        response.setStatusCode(0);
        response.setMessage("El usuario existe en la base de datos");
        try {
            LOG.info("Validando Usuario: {}", loginRequestDTO);
            boolean isUserValid = userService.isUserValid(loginRequestDTO);
            if (!isUserValid) response.setMessage("El usuario no existe en la base de datos");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setStatusCode(-1);
            response.setMessage("Error inesperado: " + e.getMessage());
            LOG.error("\"Error: {}", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
