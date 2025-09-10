package com.dashboard_gk.dashboard_gk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "type")
public class Type {
    @Id
    private String id;

    private String description;

    @CreatedDate
    private LocalDateTime registrationDate;
}
