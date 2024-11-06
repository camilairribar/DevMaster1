package com.PoloDeSalud.UBB.rest;

import com.PoloDeSalud.UBB.model.Proyecto;
import com.PoloDeSalud.UBB.service.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proyectos")
public class ProyectoController {

    @Autowired
    private ProyectoService proyectoService;

    @GetMapping
    public List<Proyecto> obtenerTodos() {
        return proyectoService.obtenerTodos();
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
    public List<Proyecto> buscarPorTitulo(@RequestParam String titulo) {
        return proyectoService.buscarPorTitulo(titulo);
    }
    @GetMapping
    public List<Proyecto> obtenerTodos(
            @RequestParam(required = false) String ordenarPor,
            @RequestParam(required = false) String direccion) {

        if ("fechaInicio".equalsIgnoreCase(ordenarPor)) {
            return proyectoService.obtenerTodosOrdenadosPorFechaInicio();
        } else if ("relevancia".equalsIgnoreCase(ordenarPor)) {
            return proyectoService.obtenerTodosOrdenadosPorRelevancia();
        } else {
            return proyectoService.obtenerTodos();
        }
    }
}
