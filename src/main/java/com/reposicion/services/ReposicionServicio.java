package com.reposicion.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reposicion.dto.ProductoDto;
import com.reposicion.feign.ClienteProductoFeign;
import com.reposicion.model.Reposicion;
import com.reposicion.repository.IReposicionRepository;

@Service
public class ReposicionServicio {

	@Autowired
	private IReposicionRepository reposicionRepo;

	@Autowired
	private ClienteProductoFeign clienteProducto;

	public List<Reposicion> listarReposiciones() {
		return reposicionRepo.findAll();
	}

	public Reposicion obtenerPReposicionPorId(Integer id) {
		return reposicionRepo.findById(id).orElse(null);
	}

	public List<Reposicion> listarPoProductoId(Integer idProducto) {
		return reposicionRepo.findByIdProducto(idProducto);
	}

	public List<Reposicion> listarPoProveedorId(Integer idProveedor) {
		return reposicionRepo.findByIdProveedor(idProveedor);
	}

	public List<Reposicion> listarPorEstado(Boolean estado) {
		return reposicionRepo.findByEstado(estado);
	}

	public Reposicion agregarReposicion(Reposicion nuevo) {
		ProductoDto producto = new ProductoDto();
		try {
			producto = clienteProducto.obtenerProductoPorId(nuevo.getIdProducto());

		} catch (Exception e) {
			throw new RuntimeException("Producto no encontrado con el Id: " + nuevo.getIdProducto());

		}
		nuevo.setNombreProducto(producto.getNombre());
		nuevo.setCategoria(producto.getCategoria());
		nuevo.setIdProveedor(producto.getIdProveedor());
		nuevo.setNombreProveedor(producto.getNombreProveedor());
		nuevo.setFechaIngreso(LocalDate.now());
		nuevo.setEstado(true);
		return reposicionRepo.save(nuevo);
	}

}
