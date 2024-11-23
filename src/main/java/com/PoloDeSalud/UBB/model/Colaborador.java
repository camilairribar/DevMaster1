package com.PoloDeSalud.UBB.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

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

    @ManyToMany
    @JoinTable(
            name = "colaborador_proyecto",
            joinColumns = @JoinColumn(name = "id_colaborador"),
            inverseJoinColumns = @JoinColumn(name = "id_proyecto")
    )
    @JsonIgnore
    private List<Proyecto> proyectos = new ArrayList<>();


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

    public List <Proyecto> getProyectos(){
        return proyectos;
    }
    
    public void setProycetos(List<Proyecto> proyectos){
        this.proyectos = proyectos;
    }

}
