package com.PoloDeSalud.UBB.rest;

import com.PoloDeSalud.UBB.model.Autor;
import com.PoloDeSalud.UBB.service.AutorService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
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
    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }
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
    public ResponseEntity<Autor> crearAutor(@RequestBody @Valid Autor autor) {
        Autor autorCreado = autorService.guardar(autor);
        return ResponseEntity.status(HttpStatus.CREATED).body(autorCreado);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAutor(@PathVariable Integer id) {
        autorService.eliminar(id);
        return ResponseEntity.noContent().build(); // Devuelve 204 (No Content)
    }

}

