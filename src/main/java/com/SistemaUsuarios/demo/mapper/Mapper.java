package com.SistemaUsuarios.demo.mapper;

import com.SistemaUsuarios.demo.dto.Response.UserResponseDto;
import com.SistemaUsuarios.demo.entity.Rol;
import com.SistemaUsuarios.demo.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class Mapper {

    public static UserResponseDto toUserResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setUsername(user.getUsername());
        userResponseDto.setNombre(user.getNombre());
        List<String> roles = user.getRoles().stream()
                        .map(Rol::getNombre)
                                .collect(Collectors.toList());
        userResponseDto.setRoles(roles);
        return userResponseDto;
    }

}
