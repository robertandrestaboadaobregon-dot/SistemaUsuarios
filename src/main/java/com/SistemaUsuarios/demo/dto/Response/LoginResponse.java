package com.SistemaUsuarios.demo.dto.Response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LoginResponse {

    private String token;
    private String tipo = "Bearer";

    private String username;
    private String nombre;
    private List<String> roles;

    public LoginResponse(String token,
                         String username,
                         String nombre,
                         List<String> roles) {
        this.token = token;
        this.username = username;
        this.nombre = nombre;
        this.roles = roles;
    }
}
