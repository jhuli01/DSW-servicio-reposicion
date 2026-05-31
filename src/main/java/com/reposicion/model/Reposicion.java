package com.reposicion.model;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reposicion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idReposicion;
	private LocalDate fechaIngreso;

	private Integer idProducto;
	private String nombreProducto;

	private String categoria;

	private Integer cantidad;

	private Double precioProducto;

	private Boolean estado = true;

	private Integer idProveedor;
	private String nombreProveedor;

}
