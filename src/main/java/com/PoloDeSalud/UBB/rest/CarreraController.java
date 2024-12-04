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

import com.PoloDeSalud.UBB.model.Carrera;
import com.PoloDeSalud.UBB.service.CarreraService;
@CrossOrigin(origins = "http://127.0.0.1:3000") // Permite CORS solo para este controlador
@RestController
@RequestMapping("/carreras")
public class CarreraController {

    @Autowired
    CarreraService carreraService;
    //@GetMapping("/ListaCarrera")
    //public ResponseEntity<List<Carrera>> obtenerTodas(){
    //    List<Carrera> lista= carreraService.obtenerTodas();
    //    return  new ResponseEntity<List<Carrera>>(lista,HttpStatus.OK);
    //}
    @GetMapping("/ListaCarrera")
    public ResponseEntity<List<Carrera>> obtenerCarreras() {
        List<Carrera> carreras = carreraService.obtenerTodas();
        return ResponseEntity.ok(carreras);
    }

    // Obtener una carrera por su ID
    @GetMapping("/{id}")
    public Carrera obtenerPorId(@PathVariable int id) {
        return carreraService.obtenerPorId(id);
    }

    // Crear una nueva carrera
    @PostMapping("/CrearCarrera")
    public Carrera crearCarrera(@RequestBody Carrera carrera) {
        return carreraService.guardar(carrera);
    }

    // Eliminar una carrera por su ID
    @DeleteMapping("EliminarCarrera/{id}")
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

