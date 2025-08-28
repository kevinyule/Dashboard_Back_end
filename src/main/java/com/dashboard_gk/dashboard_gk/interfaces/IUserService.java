package com.dashboard_gk.dashboard_gk.interfaces;

import com.dashboard_gk.dashboard_gk.dto.UserRequestDTO;
import com.dashboard_gk.dashboard_gk.dto.UserResponseDTO;

public interface IUserService {
    UserResponseDTO createUser(UserRequestDTO userRequestDTO);
}
