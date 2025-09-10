package com.dashboard_gk.dashboard_gk.controller;

import com.dashboard_gk.dashboard_gk.interfaces.ICategoryService;
import com.dashboard_gk.dashboard_gk.interfaces.IConceptService;
import com.dashboard_gk.dashboard_gk.model.Category;
import com.dashboard_gk.dashboard_gk.model.Concept;
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
@RequestMapping("/api/dashboard-gk-backend/concept-controller")
public class ConceptController {

    private static final Logger LOG = LoggerFactory.getLogger(ConceptController.class);

    @Autowired
    IConceptService conceptService;

    @PostMapping("/create-concept")
    public ResponseEntity<ObjectResponse> createCategory(@Valid @RequestBody Concept conceptRequest) {

        ObjectResponse response = new ObjectResponse();
        response.setStatusCode(0);
        response.setMessage("Concepto creado exitosamente");
        try {
            LOG.info("Creando Concepto: {}", conceptRequest);
            Concept savedConcept = conceptService.createConcept(conceptRequest);
            response.setObject(savedConcept);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setStatusCode(-1);
            response.setMessage("Error al crear Concepto: " + e.getMessage());
            LOG.error("\"Error al crear Concepto: {}", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
