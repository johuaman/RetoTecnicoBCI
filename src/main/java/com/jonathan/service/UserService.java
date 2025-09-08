package com.jonathan.service;

import com.jonathan.config.ValidationConfig;
import com.jonathan.dto.request.UserRequestDTO;
import com.jonathan.dto.response.UserResponseDTO;
import com.jonathan.entity.UserEntity;
import com.jonathan.mapper.UserMapper;
import com.jonathan.persistence.UserRepository;
import com.jonathan.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ValidationConfig validationConfig;

    @Autowired
    private JwtUtil jwtUtil;

    public UserResponseDTO registerUser(UserRequestDTO userRequest) {

        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new IllegalArgumentException("El correo ya registrado");
        }

        if (!userRequest.getEmail().matches(validationConfig.getEmailRegex())) {
            throw new IllegalArgumentException("Formato de correo inválido");
        }

        if (!userRequest.getPassword().matches(validationConfig.getPasswordRegex())) {
            throw new IllegalArgumentException("Formato de contraseña inválido");
        }

        UserEntity userEntity = userMapper.toEntity(userRequest);
        String token =  jwtUtil.generateToken(userEntity.getEmail());
        userEntity.setToken(token);
        UserEntity savedUser  = userRepository.save(userEntity);
        return userMapper.toResponseDTO(savedUser);
    }
}