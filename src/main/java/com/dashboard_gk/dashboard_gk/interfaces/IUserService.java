package com.dashboard_gk.dashboard_gk.interfaces;

import com.dashboard_gk.dashboard_gk.dto.user.CreateUserRequestDTO;
import com.dashboard_gk.dashboard_gk.dto.user.LoginRequestDTO;
import com.dashboard_gk.dashboard_gk.dto.user.UserResponseDTO;

public interface IUserService {
    UserResponseDTO createUser(CreateUserRequestDTO createUserRequestDTO);
    boolean isUserValid(LoginRequestDTO loginRequestDTO);
}
