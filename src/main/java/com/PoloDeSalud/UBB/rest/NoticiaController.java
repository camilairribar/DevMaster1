package com.PoloDeSalud.UBB.rest;

import com.PoloDeSalud.UBB.model.Noticia;
import com.PoloDeSalud.UBB.service.NoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/noticias")
public class NoticiaController {

    @Autowired
    private NoticiaService noticiaService;

    @GetMapping
    public List<Noticia> obtenerTodas() {
        return noticiaService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public Noticia obtenerPorId(@PathVariable Long id) {
        return noticiaService.obtenerPorId(id);
    }

    @PostMapping
    public Noticia crearNoticia(@RequestBody Noticia noticia) {
        return noticiaService.guardar(noticia);
    }

    @DeleteMapping("/{id}")
    public void eliminarNoticia(@PathVariable int id) {
        noticiaService.eliminar(id);
    }
}

