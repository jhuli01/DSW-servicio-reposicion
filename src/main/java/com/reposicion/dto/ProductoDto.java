package com.reposicion.dto;
import lombok.Data;

@Data
public class ProductoDto {

	private Integer idProducto;
	private String nomProd;
    private Integer stockMax;
    private Integer stockMin;
    private Integer stockActual;
    private Double precioUnit;
    private Boolean estado;
    private Integer idProveedor;
    private Integer idCategoria;
}
