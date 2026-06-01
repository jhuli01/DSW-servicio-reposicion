package com.reposicion.dto;

import lombok.Data;

@Data
public class UsuarioDto {
	private Integer idUsuario;
	private String userName;
    private String nomUsuario;
    private String apeUsuario;
    private String bni;
    private Boolean estado;
    private Integer idRol;
}
