package com.SistemaUsuarios.demo.services;

import com.SistemaUsuarios.demo.dto.Request.LoginRequest;
import com.SistemaUsuarios.demo.dto.Request.RegisterRequest;
import com.SistemaUsuarios.demo.dto.Response.LoginResponse;
import com.SistemaUsuarios.demo.entity.User;

import java.util.List;

public interface IAuthService {

    LoginResponse login(LoginRequest request);

    LoginResponse register(RegisterRequest request);

    List<User> listUsers();

}
