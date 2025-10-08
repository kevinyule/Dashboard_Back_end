package com.dashboard_gk.dashboard_gk.interfaces;

import com.dashboard_gk.dashboard_gk.dto.auth.RegisterRequestDTO;
import com.dashboard_gk.dashboard_gk.dto.auth.AuthenticationRequestDTO;
import com.dashboard_gk.dashboard_gk.dto.user.UserResponseDTO;

import java.util.List;

public interface IUserService {
    UserResponseDTO createUser(RegisterRequestDTO registerRequestDTO);
    boolean isUserValid(AuthenticationRequestDTO authenticationRequestDTO);
    List<UserResponseDTO> getAllUsers();
}
