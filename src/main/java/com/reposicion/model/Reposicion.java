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
	private Integer idProducto;
	private Integer idProveedor;
	private Integer idUsuario;
	private Integer cantRepo;
	private LocalDate fechaRepo;
	private Double precioProducto;

	private Boolean estado = true;


}
