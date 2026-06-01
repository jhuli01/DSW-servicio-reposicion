package com.reposicion.dto;

import lombok.Data;

@Data
public class ProveedorDto {
	private Integer id;
	private String nomProv;
    private String telefono;
    private String ruc;
    private String correo;
    private String contacto;
    private Boolean estado;
}
