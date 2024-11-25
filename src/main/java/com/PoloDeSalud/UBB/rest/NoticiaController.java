package com.PoloDeSalud.UBB.rest;

import com.PoloDeSalud.UBB.model.Noticia;
import com.PoloDeSalud.UBB.service.NoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

@RestController
@RequestMapping("/noticias")
public class NoticiaController {

    @Autowired
    private NoticiaService noticiaService;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


    @GetMapping
    public List<Noticia> obtenerTodas() {
        return noticiaService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public Noticia obtenerPorId(@PathVariable int id) {
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
    // Filtrar por título
    @GetMapping("/buscar/titulo")
    public List<Noticia> buscarPorTitulo(@RequestParam String titulo) {
        return noticiaService.buscarPorTitulo(titulo);
    }

    // Filtrar por rango de fechas
    @GetMapping("/buscar/fecha")
    public List<Noticia> buscarPorFecha(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date start,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date end) {
        return noticiaService.buscarPorFecha(start, end);
    }
}

