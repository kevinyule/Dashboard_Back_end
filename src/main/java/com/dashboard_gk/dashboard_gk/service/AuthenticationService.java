package com.dashboard_gk.dashboard_gk.service;

import com.dashboard_gk.dashboard_gk.dto.auth.AuthenticationRequestDTO;
import com.dashboard_gk.dashboard_gk.dto.auth.AuthenticationResponse;
import com.dashboard_gk.dashboard_gk.dto.auth.RegisterRequestDTO;
import com.dashboard_gk.dashboard_gk.model.Role;
import com.dashboard_gk.dashboard_gk.model.User;
import com.dashboard_gk.dashboard_gk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequestDTO request) {
        AuthenticationResponse response = new AuthenticationResponse();
        try {
            // Verificar si el usuario ya existe
            if (userRepository.existsByUsername(request.getUsername())) {
                response.setMessage("El usuario ya está en uso");
                return response;
            }

            if (userRepository.existsByEmail(request.getEmail())) {
                response.setMessage("El correo ya está en uso");
                return response;
            }

            // Crear el nuevo usuario
            var user = User.builder()
                    .firstName(request.getFirstName())
                    .secondName(request.getSecondName())
                    .firstLastName(request.getFirstLastName())
                    .secondLastName(request.getSecondLastName())
                    .username(request.getUsername())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .roles(Set.of(Role.ROLE_USER))
                    .enabled(true)
                    .build();

            // Guardar el usuario en MongoDB
            userRepository.save(user);

            // Generar el token JWT
            var jwtToken = jwtService.generateToken(user);

            // Retornar la respuesta
            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .firstName(user.getFirstName())
                    .secondName(user.getSecondName())
                    .firstLastName(user.getFirstLastName())
                    .secondLastName(user.getSecondLastName())
                    .roles(user.getRoles())
                    .message("Usuario registrado exitosamente")
                    .build();

        } catch (Exception e) {
            response.setMessage("Error al registrar el usuario: " + e.getMessage());
            return response;
        }
    }

    public AuthenticationResponse authenticate(AuthenticationRequestDTO request) {

        AuthenticationResponse response = new AuthenticationResponse();

        try {
            // Autenticar al usuario
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            // Si la autenticación fue exitosa, buscar el usuario
            var userOptional = userRepository.findByUsername(request.getUsername());

            if (userOptional.isEmpty()) {
                response.setMessage("Usuario no encontrado");
                return response;
            }

            var user = userOptional.get();

            // Generar el token JWT
            var jwtToken = jwtService.generateToken(user);

            // Configurar la respuesta exitosa
            response.setAccessToken(jwtToken);
            response.setUsername(user.getUsername());
            response.setEmail(user.getEmail());
            response.setFirstName(user.getFirstName());
            response.setSecondName(user.getSecondName());
            response.setFirstLastName(user.getFirstLastName());
            response.setSecondLastName(user.getSecondLastName());
            response.setRoles(user.getRoles());
            response.setMessage("Autenticación exitosa");



        } catch (BadCredentialsException e) {
            response.setMessage("Credenciales inválidas");
        } catch (Exception e) {
            response.setMessage("Error en la autenticación: " + e.getMessage());
        }

        return response;
    }
}
