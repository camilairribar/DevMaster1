package com.PoloDeSalud.UBB.rest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.PoloDeSalud.UBB.model.Noticia;
import com.PoloDeSalud.UBB.service.NoticiaService;

@CrossOrigin(origins = "http://127.0.0.1:3000") // Permite CORS solo para este controlador
@RestController
@RequestMapping("/noticias")
public class NoticiaController {

    @Autowired
    NoticiaService noticiaService;

    static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    //@GetMapping("/ListaNoticia")
    //public ResponseEntity<List<Noticia>> obtenerTodas() {
    //    List<Noticia> lista= noticiaService.obtenerTodas();
    //    return new ResponseEntity<List<Noticia>>(lista,HttpStatus.OK);
    //}
    @GetMapping("/ListaNoticia")
    public ResponseEntity<List<Noticia>> obtenerNoticias() {
        List<Noticia> noticias = noticiaService.obtenerTodas();
        return ResponseEntity.ok(noticias);
    }


    @GetMapping("/{id}")
    public Noticia obtenerPorId(@PathVariable int id) {
        return noticiaService.obtenerPorId(id);
    }

    //@PostMapping("/CrearNoticia")
    //public Noticia crearNoticia(@RequestBody Noticia noticia) {
    //    return noticiaService.guardar(noticia);
    //}
    @PostMapping("/CrearNoticia")
    public ResponseEntity<?> crearNoticia(@RequestBody Noticia noticia) {
        try {
            // Aquí, asegúrate de que la fecha esté correctamente parseada
            noticiaService.guardar(noticia);
            return ResponseEntity.status(HttpStatus.CREATED).body("Noticia creada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear noticia: " + e.getMessage());
        }
    }


    @DeleteMapping("/Eliminarnoticia/{id}")
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

