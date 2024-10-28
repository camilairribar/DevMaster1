package com.PoloDeSalud.UBB.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "colaborador")
public class Colaborador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_colaborador")
    private int idColaborador;

    @Column(name = "Nombre_colaborador", length = 100)
    private String nombreColaborador;

    @Column(name = "Correo_colaborador", length = 100)
    private String correoColaborador;

    @Column(name = "Contrasena_colaborador", length = 100)
    private String contrasenaColaborador;

    @Column(name = "Rol")
    private boolean rol;

    // Getters and Setters
    public int getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(int idColaborador) {
        this.idColaborador = idColaborador;
    }

    public String getNombreColaborador() {
        return nombreColaborador;
    }

    public void setNombreColaborador(String nombreColaborador) {
        this.nombreColaborador = nombreColaborador;
    }

    public String getCorreoColaborador() {
        return correoColaborador;
    }

    public void setCorreoColaborador(String correoColaborador) {
        this.correoColaborador = correoColaborador;
    }

    public String getContrasenaColaborador() {
        return contrasenaColaborador;
    }

    public void setContrasenaColaborador(String contrasenaColaborador) {
        this.contrasenaColaborador = contrasenaColaborador;
    }

    public boolean isRol() {
        return rol;
    }

    public void setRol(boolean rol) {
        this.rol = rol;
    }
}
