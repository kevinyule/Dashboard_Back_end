package com.dashboard_gk.dashboard_gk.service;

import com.dashboard_gk.dashboard_gk.dto.UserRequestDTO;
import com.dashboard_gk.dashboard_gk.dto.UserResponseDTO;
import com.dashboard_gk.dashboard_gk.interfaces.IUserService;
import com.dashboard_gk.dashboard_gk.model.User;
import com.dashboard_gk.dashboard_gk.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO){
        User user = new User();
        user.setName(userRequestDTO.getName());
        user.setLastName(userRequestDTO.getLastName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());

        User savedUser = userRepository.save(user);

        return new UserResponseDTO(
                savedUser.getName(),
                savedUser.getLastName(),
                savedUser.getEmail()
        );
    }
}
