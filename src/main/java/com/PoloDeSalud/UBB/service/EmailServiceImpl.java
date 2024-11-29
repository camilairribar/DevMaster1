package com.PoloDeSalud.UBB.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void enviarEmail(String nombre, String email, String mensaje) throws Exception {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        // Configura el correo
        helper.setTo("destinatario@correo.com"); // Dirección de correo destino
        helper.setSubject("Nuevo mensaje de contacto");
        helper.setText("Nombre: " + nombre + "\nCorreo: " + email + "\nMensaje: " + mensaje);

        // Envía el correo
        emailSender.send(mimeMessage);
    }
}