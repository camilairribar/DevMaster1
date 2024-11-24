package com.PoloDeSalud.UBB.rest;

import com.PoloDeSalud.UBB.model.Colaborador;
import com.PoloDeSalud.UBB.service.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/colaboradores")
public class ColaboradorController {

    // Inyección de dependencias para el servicio de colaboradores
    @Autowired
    private ColaboradorService colaboradorService;

    // Obtener todos los colaboradores
    @GetMapping
    public List<Colaborador> obtenerTodos() {
        return colaboradorService.obtenerTodos();
    }

    // Obtener un colaborador por ID
    @GetMapping("/{id}")
    public Colaborador obtenerPorId(@PathVariable Integer id) {
        return colaboradorService.obtenerPorId(id);
    }

    // Crear un nuevo colaborador
    @PostMapping
    public ResponseEntity<Colaborador> crearColaborador(@RequestBody Colaborador colaborador) {
        Colaborador colaboradorCreado = colaboradorService.guardar(colaborador);
        return ResponseEntity.status(HttpStatus.CREATED).body(colaboradorCreado);  // Devuelve el objeto con estado 201
    }

    // Eliminar un colaborador por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarColaborador(@PathVariable Integer id) {
        colaboradorService.eliminar(id);
        return ResponseEntity.noContent().build();  // Devuelve código 204 (sin contenido)
    }

    // Método para autenticar un colaborador (login)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String correo, @RequestParam String contrasena) {
        if (correo == null || contrasena == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Correo o contraseña no pueden ser nulos");  // Validación de parámetros nulos
        }

        Colaborador colaborador = colaboradorService.autenticar(correo, contrasena);
        if (colaborador != null) {
            return ResponseEntity.ok(colaborador);  // Devuelve el colaborador si las credenciales son correctas
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");  // Devuelve error 401 si las credenciales no son correctas
        }
    }
}
