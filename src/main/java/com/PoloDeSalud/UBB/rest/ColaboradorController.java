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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PoloDeSalud.UBB.model.Colaborador;
import com.PoloDeSalud.UBB.service.ColaboradorService;
@CrossOrigin(origins = "http://127.0.0.1:3000") // Permite CORS solo para este controlador
@RestController
@RequestMapping("/colaboradores")
public class ColaboradorController {

    // Inyección de dependencias para el servicio de colaboradores
    @Autowired
    ColaboradorService colaboradorService;
    @GetMapping("/ListaColaborador")
    public ResponseEntity<List<Colaborador>> obtenerTodos() {
        List<Colaborador> lista= colaboradorService.obtenerTodos();
        return new ResponseEntity<List<Colaborador>>(lista,HttpStatus.OK);
    }

    // Obtener un colaborador por ID
    @GetMapping("/{id}")
    public Colaborador obtenerPorId(@PathVariable Integer id) {
        return colaboradorService.obtenerPorId(id);
    }

    // Crear un nuevo colaborador
    @PostMapping("/CrearColaborador")
    public ResponseEntity<Colaborador> crearColaborador(@RequestBody Colaborador colaborador) {
        Colaborador colaboradorCreado = colaboradorService.guardar(colaborador);
        return ResponseEntity.status(HttpStatus.CREATED).body(colaboradorCreado);  // Devuelve el objeto con estado 201
    }

    // Eliminar un colaborador por ID
    @DeleteMapping("/EliminarColaborador/{id}")
    public ResponseEntity<Void> eliminarColaborador(@PathVariable Integer id) {
        colaboradorService.eliminar(id);
        return ResponseEntity.noContent().build();  // Devuelve código 204 (sin contenido)
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody ColaboradorLoginRequest loginRequest) {
        System.out.println("Correo recibido: " + loginRequest.getCorreo());
        System.out.println("Contraseña recibida: " + loginRequest.getContrasena());

        if (loginRequest.getCorreo() == null || loginRequest.getContrasena() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Correo o contraseña no pueden ser nulos");
        }

        Colaborador colaborador = colaboradorService.autenticar(loginRequest.getCorreo(), loginRequest.getContrasena());
        if (colaborador != null) {
            return ResponseEntity.ok(colaborador);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }
    @PostMapping("/test")
    public ResponseEntity<?> test(@RequestBody ColaboradorLoginRequest loginRequest) {
        return ResponseEntity.ok("Correo: " + loginRequest.getCorreo() + ", Contraseña: " + loginRequest.getContrasena());
    }


    // Crear una clase para el cuerpo de la solicitud
    public static class ColaboradorLoginRequest {
    
        private String correo;
        private String contrasena;

        // Getter para 'correo'
        public String getCorreo() {
            return correo;
        }

        // Setter para 'correo'
        public void setCorreo(String correo) {
            this.correo = correo;
        }

        // Getter para 'contrasena'
        public String getContrasena() {
            return contrasena;
        }

        // Setter para 'contrasena'
        public void setContrasena(String contrasena) {
            this.contrasena = contrasena;
        }
    }


}
