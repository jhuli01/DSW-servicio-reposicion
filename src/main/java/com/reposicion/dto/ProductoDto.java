package com.reposicion.dto;
import lombok.Data;

@Data
public class ProductoDto {

	private Integer id;
	private String nombre;
    private String categoria;
    private Integer idProveedor;
    private String nombreProveedor;
    private int stockActual;
    private int stockMinimo;
}
