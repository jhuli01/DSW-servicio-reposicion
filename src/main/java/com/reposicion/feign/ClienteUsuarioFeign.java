package com.reposicion.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.reposicion.dto.UsuarioDto;

@FeignClient(name = "usuario-service", url = "http://localhost:8084/api/usuarios")
public interface ClienteUsuarioFeign {
	@GetMapping("/{id}")
	UsuarioDto obtenerUsuarioPorId(@PathVariable Integer id);
}
