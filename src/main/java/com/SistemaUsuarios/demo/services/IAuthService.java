package com.SistemaUsuarios.demo.services;

import com.SistemaUsuarios.demo.dto.Request.LoginRequest;
import com.SistemaUsuarios.demo.dto.Request.RegisterRequest;
import com.SistemaUsuarios.demo.dto.Response.LoginResponse;

public interface IAuthService {

    String login(LoginRequest request);

    LoginResponse register(RegisterRequest request);

}
