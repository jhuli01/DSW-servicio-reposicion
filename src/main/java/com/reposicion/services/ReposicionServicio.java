package com.reposicion.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reposicion.dto.ProductoDto;
import com.reposicion.dto.ProveedorDto;
import com.reposicion.dto.UsuarioDto;
import com.reposicion.feign.ClienteProductoFeign;
import com.reposicion.feign.ClienteProveedorFeign;
import com.reposicion.feign.ClienteUsuarioFeign;
import com.reposicion.model.Reposicion;
import com.reposicion.repository.IReposicionRepository;

@Service
public class ReposicionServicio {

	@Autowired
	private IReposicionRepository reposicionRepo;

	@Autowired
	private ClienteProductoFeign clienteProducto;
	@Autowired
	private ClienteProveedorFeign clienteProveedor;
	@Autowired
	private ClienteUsuarioFeign clienteUsuario;

	public List<Reposicion> listarReposiciones() {
		return reposicionRepo.findAll();
	}

	public Reposicion obtenerReposicionPorId(Integer id) {
		return reposicionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Reposición no encontrada con Id: " + id));
	}

	public List<Reposicion> listarPorProductoId(Integer idProducto) {
		return reposicionRepo.findByIdProducto(idProducto);
	}

	public List<Reposicion> listarPorProveedorId(Integer idProveedor) {
		return reposicionRepo.findByIdProveedor(idProveedor);
	}
	
	public List<Reposicion> listarPorUsuarioId(Integer idUsuario) {
		return reposicionRepo.findByIdUsuario(idUsuario);
	}

	public List<Reposicion> listarPorEstado(Boolean estado) {
		return reposicionRepo.findByEstado(estado);
	}

	public Reposicion agregarReposicion(Reposicion nuevo) {
		ProductoDto producto;
		ProveedorDto proveedor;
		UsuarioDto usuario;
		
		try {
			producto = clienteProducto.obtenerProductoPorId(nuevo.getIdProducto());
			nuevo.setPrecioProducto(producto.getPrecioUnit());

		} catch (Exception e) {
			throw new RuntimeException("Producto no encontrado con el Id: " + nuevo.getIdProducto());
		}
		
		try {
			proveedor = clienteProveedor.obtenerProveedorPorId(nuevo.getIdProveedor());

		} catch (Exception e) {
			throw new RuntimeException("Proveedor no encontrado con el Id: " + nuevo.getIdProveedor());
		}
		
		try {
			usuario = clienteUsuario.obtenerUsuarioPorId(nuevo.getIdUsuario());

		} catch (Exception e) {
			throw new RuntimeException("Usuario no encontrado con el Id: " + nuevo.getIdUsuario());
		}
		
        nuevo.setEstado(true);
		
		return reposicionRepo.save(nuevo);
	}
	
	
	public Reposicion actualizarReposicion(Integer id, Reposicion repo) {
        Reposicion reposicion = reposicionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Reposición no encontrada con Id: " + id));
 
        if (!reposicion.getEstado()) {
            throw new RuntimeException("No se puede modificar una reposición cancelada");
        }
 
        reposicion.setCantRepo(repo.getCantRepo());
        reposicion.setPrecioProducto(repo.getPrecioProducto());
        reposicion.setFechaRepo(repo.getFechaRepo());
        return reposicionRepo.save(reposicion);
    }
	
	
	public Reposicion cancelarReposicion(Integer id) {
		Reposicion reposicion = reposicionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Reposición no encontrada con Id: " + id));
        reposicion.setEstado(false);
        return reposicionRepo.save(reposicion);
    }

}
