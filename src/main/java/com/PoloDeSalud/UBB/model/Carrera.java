package com.PoloDeSalud.UBB.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "carrera")
public class Carrera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carreras")
    private int idCarreras;

    @Column(name = "Nombre_carrera", length = 100)
    @JsonProperty("nombre")
    private String nombreCarrera;

    @Column(name = "Descripcion_carrera", columnDefinition = "TEXT")
    @JsonProperty("descripcion")
    private String descripcionCarrera;

    @Column(name = "Facultad", length = 100)
    @JsonProperty("facultad")
    private String facultad;

    @ManyToMany(mappedBy = "carreras")
    @JsonIgnore // Evita ciclos durante la serialización JSON
    private List<Proyecto> proyectos = new ArrayList<>();


    // Getters and Setters
    public int getIdCarreras() {
        return idCarreras;
    }

    public void setIdCarreras(int idCarreras) {
        this.idCarreras = idCarreras;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    public String getDescripcionCarrera() {
        return descripcionCarrera;
    }

    public void setDescripcionCarrera(String descripcionCarrera) {
        this.descripcionCarrera = descripcionCarrera;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public List<Proyecto> getProyectos() {
    return proyectos;
    }

    public void setProyectos(List<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }
}
