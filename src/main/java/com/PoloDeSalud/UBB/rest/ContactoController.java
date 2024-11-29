package com.PoloDeSalud.UBB.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.PoloDeSalud.UBB.service.EmailService;
@RestController
@RequestMapping("/contacto")
@CrossOrigin(origins = "http://localhost:3000") // Permitir solicitudes desde el puerto 3000
public class ContactoController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/enviar-contacto")
    public ResponseEntity<String> enviarFormulario(@RequestParam String nombre, 
                                                  @RequestParam String email, 
                                                  @RequestParam String mensaje) {
        try {
            System.out.println("Datos recibidos: ");
            System.out.println("Nombre: " + nombre);
            System.out.println("Email: " + email);
            System.out.println("Mensaje: " + mensaje);

            // emailService.enviarEmail(nombre, email, mensaje);

            return ResponseEntity.ok("{\"message\":\"¡Gracias por tu mensaje, " + nombre + "! Nos pondremos en contacto pronto.\"}");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("{\"message\":\"Hubo un error al enviar el mensaje. Inténtalo de nuevo.\"}");
        }
    }
}


