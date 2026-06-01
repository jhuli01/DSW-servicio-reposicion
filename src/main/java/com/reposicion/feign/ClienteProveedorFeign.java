package com.reposicion.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.reposicion.dto.ProveedorDto;

@FeignClient(name = "proveedor-service", url = "http://localhost:8083/api/proveedores")
public interface ClienteProveedorFeign {
	@GetMapping("/{id}")
	ProveedorDto obtenerProveedorPorId(@PathVariable Integer id);
}
