package com.dashboard_gk.dashboard_gk.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private String firstName;
    private String secondName;
    private String firstLastName;
    private String secondLastName;
    private String email;
}
