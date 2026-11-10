package com.SistemaUsuarios.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private boolean exito;
    private String mensaje;
    private T data;

    public static <T> ApiResponse<T> ok(String mensaje,T data){
        return new ApiResponse<>(true,mensaje,data);
    }

    public static <T> ApiResponse<T> error(String mensaje){
        return new ApiResponse<>(false,mensaje,null);
    }
}
