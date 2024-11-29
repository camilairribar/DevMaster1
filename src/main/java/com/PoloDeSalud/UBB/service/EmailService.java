package com.PoloDeSalud.UBB.service;

public interface EmailService {

    void enviarEmail(String nombre, String email, String mensaje) throws Exception;
}