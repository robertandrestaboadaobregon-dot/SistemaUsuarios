package com.SistemaUsuarios.demo.services.impl;

import com.SistemaUsuarios.demo.dto.Request.LoginRequest;
import com.SistemaUsuarios.demo.dto.Request.RegisterRequest;
import com.SistemaUsuarios.demo.dto.Response.LoginResponse;
import com.SistemaUsuarios.demo.entity.Rol;
import com.SistemaUsuarios.demo.entity.User;
import com.SistemaUsuarios.demo.repository.RolRepository;
import com.SistemaUsuarios.demo.repository.UsuarioRepository;
import com.SistemaUsuarios.demo.services.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtServiceImpl jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;



    @Override
    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword())
        );

        var usuario = usuarioRepository.findByUsername(request.getUsername()).orElseThrow();
        var userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        var token = jwtService.generarToken(userDetails);

        List<String> roles = usuario.getRoles().stream()
                .map(Rol::getNombre).toList();

        return new LoginResponse(token,usuario.getUsername(),usuario.getNombre(),roles);
    }

    @Override
    @Transactional
    public LoginResponse register(RegisterRequest request) {
        // busqueda  si el usuario esta en usoo
        if(usuarioRepository.existsByUsername(request.getUsername())){
            throw new RuntimeException("El username ya esta en uso" + request.getUsername());
        }

        Rol rol = rolRepository.findByName(request.getRol())
                .orElseThrow(()-> new RuntimeException("Rol no encontrado " + request.getRol()));

        User usuario = new User();
        usuario.setUsername(request.getUsername());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setNombre(request.getNombre());
        usuario.setActivo(true);
        usuario.setRoles(Set.of(rol));
        usuarioRepository.save(usuario);

        var userDetails =
                userDetailsService.
                        loadUserByUsername(request.getUsername());
        var token = jwtService.generarToken(userDetails);

        return new LoginResponse(
                token,
                usuario.getUsername(),
                usuario.getNombre(),
                List.of(rol.getNombre()));
    }
}
