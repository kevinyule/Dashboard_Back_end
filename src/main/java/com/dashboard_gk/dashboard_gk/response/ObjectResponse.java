package com.dashboard_gk.dashboard_gk.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ObjectResponse {
    private Integer statusCode;
    private String message;
    private Object object;
}
