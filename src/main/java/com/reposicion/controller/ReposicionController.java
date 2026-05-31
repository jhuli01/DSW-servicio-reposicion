package com.reposicion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reposicion.model.Reposicion;
import com.reposicion.services.ReposicionServicio;

@RestController
@RequestMapping("/api/reposiciones")
public class ReposicionController {


    @Autowired
    private ReposicionServicio reposicionServicio;
 
    @GetMapping
    public ResponseEntity<List<Reposicion>> listarReposiciones() {
        List<Reposicion> lista = reposicionServicio.listarReposiciones();
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<Reposicion> obtenerReposicion(@PathVariable Integer id) {
        Reposicion reposicion = reposicionServicio.obtenerPReposicionPorId(id);
        if (reposicion == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(reposicion);
    }
 
    @GetMapping("/producto/{idProducto}")
    public ResponseEntity<List<Reposicion>> listarPorProducto(@PathVariable Integer idProducto) {
        List<Reposicion> lista = reposicionServicio.listarPoProductoId(idProducto);
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }
 
    @GetMapping("/proveedor/{idProveedor}")
    public ResponseEntity<List<Reposicion>> listarPorProveedor(@PathVariable Integer idProveedor) {
        List<Reposicion> lista = reposicionServicio.listarPoProveedorId(idProveedor);
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }
 
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Reposicion>> listarPorEstado(@PathVariable String estado) {
        List<Reposicion> lista = reposicionServicio.listarPorEstado(estado);
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }
 
    @PostMapping
    public ResponseEntity<?> registrarReposicion(@RequestBody Reposicion nuevo) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(reposicionServicio.agregarReposicion(nuevo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear la reposición: " + e.getMessage());
        }
    }
 
}