package com.dashboard_gk.dashboard_gk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "movements")
public class Movements {
    @Id
    private String id;

    private BigDecimal budget;
    private BigDecimal amount;
    private String userId;
    private String conceptId;
    private String categoryId;
    private String typeId;

    @CreatedDate
    private LocalDateTime registrationDate;
}
