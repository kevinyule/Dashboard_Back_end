package com.dashboard_gk.dashboard_gk.controller;

import com.dashboard_gk.dashboard_gk.interfaces.ICategoryService;
import com.dashboard_gk.dashboard_gk.interfaces.ITypeService;
import com.dashboard_gk.dashboard_gk.model.Category;
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
@RequestMapping("/api/dashboard-gk-backend/category-controller")
public class CategoryController {

    private static final Logger LOG = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    ICategoryService categoryService;

    @PostMapping("/create-category")
    public ResponseEntity<ObjectResponse> createCategory(@Valid @RequestBody Category categoryRequest){

        ObjectResponse response = new ObjectResponse();
        response.setStatusCode(0);
        response.setMessage("Categoría creada exitosamente");
        try {
            LOG.info("Creando Categoría: {}", categoryRequest);
            Category categoryCreated = categoryService.createCategory(categoryRequest);
            response.setObject(categoryCreated);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setStatusCode(-1);
            response.setMessage("Error al crear Categoría: " + e.getMessage());
            LOG.error("\"Error al crear Categoría: {}", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
