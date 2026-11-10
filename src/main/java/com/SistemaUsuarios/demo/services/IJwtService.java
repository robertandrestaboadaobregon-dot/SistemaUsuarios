package com.SistemaUsuarios.demo.services;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

public interface IJwtService {

    String generarToken(UserDetails userDetails);
    boolean esTokenValido(String token,UserDetails userDetails);
    String extraerUsername(String token);
    Date extraerExpiracion(String token);
    boolean estokenExpirado(String token);
    Claims extraerTodoClaims(String token);
}
