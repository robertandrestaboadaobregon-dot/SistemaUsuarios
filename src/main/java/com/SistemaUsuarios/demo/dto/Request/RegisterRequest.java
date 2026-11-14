package com.SistemaUsuarios.demo.dto.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String nombre;

    private String rol = "ADMIN";

}
