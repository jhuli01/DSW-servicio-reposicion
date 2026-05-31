package com.reposicion.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.reposicion.dto.ProductoDto;

@FeignClient(name = "producto-service", url = "http://localhost:8081/api/productos")
public interface ClienteProductoFeign {

	@GetMapping("/{id}")
	ProductoDto obtenerProductoPorId(@PathVariable Integer id);
}
