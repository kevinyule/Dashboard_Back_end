package com.dashboard_gk.dashboard_gk.controller;

import com.dashboard_gk.dashboard_gk.interfaces.ITypeService;
import com.dashboard_gk.dashboard_gk.model.Type;
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
@RequestMapping("/api/dashboard-gk-backend/type-controller")
public class TypeController {

    private static final Logger LOG = LoggerFactory.getLogger(TypeController.class);

    @Autowired
    ITypeService typeService;

    @PostMapping("/create-type")
    public ResponseEntity<ObjectResponse> createType(@Valid @RequestBody Type typeRequest){

        ObjectResponse response = new ObjectResponse();
        response.setStatusCode(0);
        response.setMessage("Tipo creado exitosamente");
        try {
            LOG.info("Creando Tipo: {}", typeRequest);
            Type typeCreated = typeService.createType(typeRequest);
            response.setObject(typeCreated);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setStatusCode(-1);
            response.setMessage("Error al crear tipo: " + e.getMessage());
            LOG.error("\"Error al crear tipo: {}", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
