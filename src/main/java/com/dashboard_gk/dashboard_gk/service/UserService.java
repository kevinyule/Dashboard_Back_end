package com.dashboard_gk.dashboard_gk.service;

import com.dashboard_gk.dashboard_gk.dto.auth.RegisterRequestDTO;
import com.dashboard_gk.dashboard_gk.dto.auth.AuthenticationRequestDTO;
import com.dashboard_gk.dashboard_gk.dto.user.UserResponseDTO;
import com.dashboard_gk.dashboard_gk.interfaces.IUserService;
import com.dashboard_gk.dashboard_gk.model.User;
import com.dashboard_gk.dashboard_gk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    public UserResponseDTO createUser(RegisterRequestDTO registerRequestDTO){


        if (userRepository.existsByEmail(registerRequestDTO.getEmail())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }
        String password = registerRequestDTO.getPassword();
        String hashedPassword = new BCryptPasswordEncoder().encode(password);

        User user = new User();
        user.setFirstName(registerRequestDTO.getFirstName());
        user.setSecondName(registerRequestDTO.getSecondName());
        user.setFirstLastName(registerRequestDTO.getFirstLastName());
        user.setSecondLastName(registerRequestDTO.getSecondLastName());
        user.setEmail(registerRequestDTO.getEmail());
        user.setUsername(registerRequestDTO.getUsername());
        user.setPassword(hashedPassword);

        User savedUser = userRepository.save(user);

        return new UserResponseDTO(
                savedUser.getFirstName(),
                savedUser.getSecondName(),
                savedUser.getFirstLastName(),
                savedUser.getSecondLastName(),
                savedUser.getEmail(),
                savedUser.getUsername()
        );
    }

    @Override
    public boolean isUserValid(AuthenticationRequestDTO authenticationRequestDTO) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String password = authenticationRequestDTO.getPassword();
        return userRepository.findByUsername(authenticationRequestDTO.getUsername()).filter(user -> passwordEncoder.matches(password, user.getPassword())).isPresent();
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> new UserResponseDTO(
                user.getFirstName(),
                user.getSecondName(),
                user.getFirstLastName(),
                user.getSecondLastName(),
                user.getEmail(),
                user.getUsername()
        )).toList();
    }
}
