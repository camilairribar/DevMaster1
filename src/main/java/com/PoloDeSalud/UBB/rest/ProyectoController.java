package com.PoloDeSalud.UBB.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.PoloDeSalud.UBB.model.Proyecto;
import com.PoloDeSalud.UBB.service.ProyectoService;

@CrossOrigin(origins = "http://127.0.0.1:3000") // Permite CORS solo para este controlador
@RestController
@RequestMapping("/proyectos")
public class ProyectoController {

    @Autowired
    ProyectoService proyectoService;

    @GetMapping("/ListaProyecto")
    public ResponseEntity<List<Proyecto>> obtenerTodos() {
        List<Proyecto> lista= proyectoService.obtenerTodos();
        return new ResponseEntity<List<Proyecto>>(lista,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Proyecto obtenerPorId(@PathVariable int id) {
        return proyectoService.obtenerPorId(id);
    }

    @PostMapping
    public Proyecto crearProyecto(@RequestBody Proyecto proyecto) {
        return proyectoService.guardar(proyecto);
    }

    @DeleteMapping("/{id}")
    public void eliminarProyecto(@PathVariable int id) {
        proyectoService.eliminar(id);
    }
    //nuevos metodos
    @GetMapping("/buscar")
    public List<Proyecto> buscarPorNombre(@RequestParam String nombre) {
        return proyectoService.buscarPorNombre(nombre);
    }

    @PutMapping("/{id}")
    public Proyecto actualizarProyecto(@PathVariable int id, @RequestBody Proyecto proyecto) {
        return proyectoService.actualizarProyecto(id, proyecto);
    }
    
}
