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

    @GetMapping
    public List<Carrera> obtenerTodas() {
        return carreraService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public Carrera obtenerPorId(@PathVariable int id) {
        return carreraService.obtenerPorId(id);
    }

    @PostMapping
    public Carrera crearCarrera(@RequestBody Carrera carrera) {
        return carreraService.guardar(carrera);
    }

    @DeleteMapping("/{id}")
    public void eliminarCarrera(@PathVariable int id) {
        carreraService.eliminar(id);
    }
}
