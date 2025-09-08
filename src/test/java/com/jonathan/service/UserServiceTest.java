package com.jonathan.service;

import com.jonathan.config.ValidationConfig;
import com.jonathan.dto.request.UserRequestDTO;
import com.jonathan.dto.response.UserResponseDTO;
import com.jonathan.entity.UserEntity;
import com.jonathan.mapper.UserMapper;
import com.jonathan.persistence.UserRepository;
import com.jonathan.security.JwtUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private ValidationConfig validationConfig;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private UserService userService;

    private UserRequestDTO request;
    private UserEntity entity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        request = new UserRequestDTO();
        request.setName("Jonathan");
        request.setEmail("jonathan@gmail.com");
        request.setPassword("Clave2025");

        entity = new UserEntity();
        entity.setEmail(request.getEmail());
        entity.setPassword(request.getPassword());
    }

    @Test
    void registerUser_Successfull() {

        when(userRepository.existsByEmail(request.getEmail())).thenReturn(false);
        when(validationConfig.getEmailRegex()).thenReturn("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
        when(validationConfig.getPasswordRegex()).thenReturn("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$");
        when(userMapper.toEntity(request)).thenReturn(entity);
        when(jwtUtil.generateToken(request.getEmail())).thenReturn("fake-jwt-token");
        when(userRepository.save(any(UserEntity.class))).thenReturn(entity);

        UserResponseDTO out = new UserResponseDTO();
        out.setId("123456");
        out.setToken("fake-jwt-token");
        when(userMapper.toResponseDTO(entity)).thenReturn(out);

        UserResponseDTO response = userService.registerUser(request);

        assertNotNull(response);
        assertEquals("123456", response.getId());
        assertEquals("fake-jwt-token", response.getToken());
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    void registerUser_Fails_WhenEmailAlreadyExists() {
        when(userRepository.existsByEmail(request.getEmail())).thenReturn(true);
        Assertions.assertThatThrownBy(() -> userService.registerUser(request))
                .hasMessage("El correo ya registrado");
    }

    @Test
    void registerUser_Fails_WhenEmailFormatInvalid() {
        when(userRepository.existsByEmail(request.getEmail())).thenReturn(false);
        when(validationConfig.getEmailRegex()).thenReturn("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
        request.setEmail("jonathan");
        Assertions.assertThatThrownBy(() -> userService.registerUser(request))
                .hasMessage("Formato de correo inválido");
    }

    @Test
    void registerUser_Fails_WhenPasswordInvalid() {
        when(userRepository.existsByEmail(request.getEmail())).thenReturn(false);
        when(validationConfig.getEmailRegex()).thenReturn("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
        when(validationConfig.getPasswordRegex()).thenReturn("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$");

        request.setEmail("jonathan@gmail.com");
        request.setPassword("1234");
        Assertions.assertThatThrownBy(() -> userService.registerUser(request))
                .hasMessage("Formato de contraseña inválido");
    }

}