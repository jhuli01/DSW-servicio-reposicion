package com.reposicion.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reposicion.model.Reposicion;

@Repository
public interface IReposicionRepository extends JpaRepository<Reposicion, Integer> {

	List<Reposicion> findByIdProducto(Integer idProducto);
    List<Reposicion> findByIdProveedor(Integer idProveedor);
    
    List<Reposicion> findByEstado(Boolean estado);
    
}
