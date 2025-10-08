package com.dashboard_gk.dashboard_gk.controller;

import com.dashboard_gk.dashboard_gk.dto.auth.RegisterRequestDTO;
import com.dashboard_gk.dashboard_gk.dto.auth.AuthenticationRequestDTO;
import com.dashboard_gk.dashboard_gk.dto.user.UserResponseDTO;
import com.dashboard_gk.dashboard_gk.interfaces.IUserService;
import com.dashboard_gk.dashboard_gk.response.ObjectResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard-gk-backend/user-controller")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    IUserService userService;

    @PostMapping("/create-user")
    public ResponseEntity<ObjectResponse> createUser(@Valid @RequestBody RegisterRequestDTO registerRequestDTO){

        ObjectResponse response = new ObjectResponse();
        response.setStatusCode(0);
        response.setMessage("Usuario creado exitosamente");
        try {
            LOG.info("Creando Usuario: {}", registerRequestDTO);
            UserResponseDTO userResponseDTO = userService.createUser(registerRequestDTO);
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
    public ResponseEntity<ObjectResponse> isUserValid(@Valid @RequestBody AuthenticationRequestDTO authenticationRequestDTO){

        ObjectResponse response = new ObjectResponse();
        response.setStatusCode(0);
        response.setMessage("El usuario existe en la base de datos");
        try {
            LOG.info("Validando Usuario: {}", authenticationRequestDTO);
            boolean isUserValid = userService.isUserValid(authenticationRequestDTO);
            if (!isUserValid) response.setMessage("El usuario no existe en la base de datos");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setStatusCode(-1);
            response.setMessage("Error inesperado: " + e.getMessage());
            LOG.error("\"Error: {}", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

/*
    @GetMapping("/get-all-users")
    public  ResponseEntity<ObjectResponse> getAllUsers(){
        ObjectResponse response = new ObjectResponse();
        response.setStatusCode(0);
        response.setMessage("Usuarios obtenidos exitosamente");
        try {
            LOG.info("Obteniendo todos los usuarios");
            response.setObject(userService.getAllUsers());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setStatusCode(-1);
            response.setMessage("Error al obtener usuarios: " + e.getMessage());
            LOG.error("\"Error al obtener usuarios: {}", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }*/
}
