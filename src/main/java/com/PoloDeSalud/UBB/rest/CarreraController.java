package com.PoloDeSalud.UBB.rest;

import com.PoloDeSalud.UBB.model.Carrera;
import com.PoloDeSalud.UBB.service.CarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carreras")
public class CarreraController {

    @Autowired
    private CarreraService carreraService;

    // Obtener todas las carreras, con opción de ordenarlas
    @GetMapping
    public List<Carrera> obtenerTodas(@RequestParam(required = false, defaultValue = "false") boolean ordenadas) {
        if (ordenadas) {
            return carreraService.obtenerTodasOrdenadas();
        } else {
            return carreraService.obtenerTodas();
        }
    }

    // Obtener una carrera por su ID
    @GetMapping("/{id}")
    public Carrera obtenerPorId(@PathVariable int id) {
        return carreraService.obtenerPorId(id);
    }

    // Crear una nueva carrera
    @PostMapping
    public Carrera crearCarrera(@RequestBody Carrera carrera) {
        return carreraService.guardar(carrera);
    }

    // Eliminar una carrera por su ID
    @DeleteMapping("/{id}")
    public void eliminarCarrera(@PathVariable int id) {
        carreraService.eliminar(id);
    }

    // Actualizar una carrera por su ID
    @PutMapping("/{id}")
    public Carrera actualizarCarrera(@PathVariable int id, @RequestBody Carrera carrera) {
        carrera.setIdCarreras(id);
        return carreraService.actualizar(carrera);
    }

    // Buscar carreras por nombre
    @GetMapping("/buscar")
    public List<Carrera> buscarPorNombre(@RequestParam String nombre) {
        return carreraService.buscarPorNombre(nombre);
    }
}

