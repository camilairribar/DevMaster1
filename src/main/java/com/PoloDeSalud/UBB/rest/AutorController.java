package com.PoloDeSalud.UBB.rest;

import com.PoloDeSalud.UBB.model.Autor;
import com.PoloDeSalud.UBB.service.AutorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;


    @PostMapping("/{idAutor}/noticias/{idNoticia}")
    public ResponseEntity<?> asociarNoticia(@PathVariable int idAutor, @PathVariable int idNoticia) {
        try {
            Autor autor = autorService.asociarNoticia(idAutor, idNoticia);
            return ResponseEntity.ok(autor);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @GetMapping
    public List<Autor> obtenerTodos() {
        return autorService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Autor obtenerPorId(@PathVariable Integer id) {
        return autorService.obtenerPorId(id);
    }
    //HU-18: Registrar un Nuevo Autor
    @PostMapping
    public Autor crearAutor(@RequestBody Autor autor) {
        return autorService.guardar(autor);
    }

    @DeleteMapping("/{id}")
    public void eliminarAutor(@PathVariable Integer id) {
        autorService.eliminar(id);
    }
}

