package com.SistemaUsuarios.demo.controller;


import com.SistemaUsuarios.demo.dto.ApiResponse;
import com.SistemaUsuarios.demo.dto.Request.LoginRequest;
import com.SistemaUsuarios.demo.dto.Request.RegisterRequest;
import com.SistemaUsuarios.demo.dto.Response.LoginResponse;
import com.SistemaUsuarios.demo.services.impl.AuthServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Autenticación", description = "Login y registro de usuarios")
public class AuthController {
    private final AuthServiceImpl authService;

    @Operation(summary = "Iniciar sesión", description = "Retorna un token JWT válido por 24 horas.")
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(
            @Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(ApiResponse.ok("Login exitoso", authService.login(request)));
    }

    @Operation(summary = "Registrar nuevo usuario")
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<LoginResponse>> register(
            @Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(ApiResponse.ok("Usuario registrado exitosamente", authService.register(request)));
    }
}
