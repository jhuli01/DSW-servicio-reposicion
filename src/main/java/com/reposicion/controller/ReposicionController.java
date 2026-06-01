package com.reposicion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
        Reposicion reposicion = reposicionServicio.obtenerReposicionPorId(id);
        if (reposicion == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(reposicion);
    }
 
    @GetMapping("/producto/{idProducto}")
    public ResponseEntity<List<Reposicion>> listarPorProducto(@PathVariable Integer idProducto) {
        List<Reposicion> lista = reposicionServicio.listarPorProductoId(idProducto);
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }
 
    @GetMapping("/proveedor/{idProveedor}")
    public ResponseEntity<List<Reposicion>> listarPorProveedor(@PathVariable Integer idProveedor) {
        List<Reposicion> lista = reposicionServicio.listarPorProveedorId(idProveedor);
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }
    
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Reposicion>> listarPorUsuario(@PathVariable Integer idUsuario) {
        List<Reposicion> lista = reposicionServicio.listarPorUsuarioId(idUsuario);
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }
 
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Reposicion>> listarPorEstado(@PathVariable Boolean estado) {
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
    
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarReposicion(@PathVariable Integer id,
                                                   @RequestBody Reposicion repo) {
        try {
            return ResponseEntity.ok(reposicionServicio.actualizarReposicion(id, repo));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PutMapping("/cancelar/{id}")
    public ResponseEntity<?> cancelarReposicion(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(reposicionServicio.cancelarReposicion(id));
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
 
}