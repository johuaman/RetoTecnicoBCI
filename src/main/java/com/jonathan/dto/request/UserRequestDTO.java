package com.jonathan.dto.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import java.util.List;

@Data
public class UserRequestDTO {

    @JsonAlias({"name", "Name"})
    @NotBlank(message = "El campo es requerido.")
    @NotEmpty
    private String name;

    @JsonAlias({"email", "Email"})
    @NotBlank(message = "El campo es requerido.")
    @Email(message = "Formato no válido.")
    @NotEmpty
    private String email;

    @JsonAlias({"password", "Password"})
    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    private List<PhoneRequestDTO> phones;
}