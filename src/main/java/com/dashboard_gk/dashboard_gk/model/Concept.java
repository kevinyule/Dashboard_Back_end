package com.dashboard_gk.dashboard_gk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "concept")
public class Concept {
    @Id
    private String id;
    private String userId;
    private String description;
    private String categoryId;
    private String typeId;

    @CreatedDate
    private LocalDateTime registrationDate;
}
