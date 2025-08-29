package com.dashboard_gk.dashboard_gk.service;

import com.dashboard_gk.dashboard_gk.dto.user.CreateUserRequestDTO;
import com.dashboard_gk.dashboard_gk.dto.user.LoginRequestDTO;
import com.dashboard_gk.dashboard_gk.dto.user.UserResponseDTO;
import com.dashboard_gk.dashboard_gk.interfaces.IUserService;
import com.dashboard_gk.dashboard_gk.model.User;
import com.dashboard_gk.dashboard_gk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    public UserResponseDTO createUser(CreateUserRequestDTO createUserRequestDTO){


        if (userRepository.existsByEmail(createUserRequestDTO.getEmail())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }
        String password = createUserRequestDTO.getPassword();
        String hashedPassword = new BCryptPasswordEncoder().encode(password);

        User user = new User();
        user.setFirstName(createUserRequestDTO.getFirstName());
        user.setSecondName(createUserRequestDTO.getSecondName());
        user.setFirstLastName(createUserRequestDTO.getFirstLastName());
        user.setSecondLastName(createUserRequestDTO.getSecondLastName());
        user.setEmail(createUserRequestDTO.getEmail());
        user.setPassword(hashedPassword);

        User savedUser = userRepository.save(user);

        return new UserResponseDTO(
                savedUser.getFirstName(),
                savedUser.getSecondName(),
                savedUser.getFirstLastName(),
                savedUser.getSecondLastName(),
                savedUser.getEmail()
        );
    }

    @Override
    public boolean isUserValid(LoginRequestDTO loginRequestDTO) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String password = loginRequestDTO.getPassword();

        return userRepository.findByEmail(loginRequestDTO.getEmail()).filter(user -> passwordEncoder.matches(password, user.getPassword())).isPresent();
    }
}
