package com.PoloDeSalud.UBB.rest;

import com.PoloDeSalud.UBB.model.Autor;
import com.PoloDeSalud.UBB.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @GetMapping
    public List<Autor> obtenerTodos() {
        return autorService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Autor obtenerPorId(@PathVariable Integer id) {
        return autorService.obtenerPorId(id);
    }

    @PostMapping
    public Autor crearAutor(@RequestBody Autor autor) {
        return autorService.guardar(autor);
    }

    @DeleteMapping("/{id}")
    public void eliminarAutor(@PathVariable Integer id) {
        autorService.eliminar(id);
    }
}

