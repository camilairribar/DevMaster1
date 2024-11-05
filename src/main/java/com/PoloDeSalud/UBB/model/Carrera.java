package com.PoloDeSalud.UBB.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

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
}
