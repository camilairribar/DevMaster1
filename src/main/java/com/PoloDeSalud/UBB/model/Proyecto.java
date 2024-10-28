package com.PoloDeSalud.UBB.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

import java.util.Date;

@Entity
@Table(name = "proyecto")
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proyecto")
    private int idProyecto;

    @Column(name = "Nombre_proyecto", length = 100)
    @JsonProperty("nombre")
    private String nombreProyecto;

    @Column(name = "Descripcion_proyecto", columnDefinition = "TEXT")
    @JsonProperty("descripcion")
    private String descripcionProyecto;

    @Column(name = "Fecha_publicacion_proyecto")
    @JsonProperty("fechaPublicacion")
    private Date fechaPublicacionProyecto;

    @Column(name = "Fecha_termino_proyecto")
    @JsonProperty("fechaTermino")
    private Date fechaTerminoProyecto;

    @Column(name = "Estado_proyecto", length = 50)
    @JsonProperty("estado")
    private String estadoProyecto;

    @Column(name = "Foto_proyecto", length = 255)
    @JsonProperty("foto")
    private String fotoProyecto;

    // Getters and Setters
    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public String getDescripcionProyecto() {
        return descripcionProyecto;
    }

    public void setDescripcionProyecto(String descripcionProyecto) {
        this.descripcionProyecto = descripcionProyecto;
    }

    public Date getFechaPublicacionProyecto() {
        return fechaPublicacionProyecto;
    }

    public void setFechaPublicacionProyecto(Date fechaPublicacionProyecto) {
        this.fechaPublicacionProyecto = fechaPublicacionProyecto;
    }

    public Date getFechaTerminoProyecto() {
        return fechaTerminoProyecto;
    }

    public void setFechaTerminoProyecto(Date fechaTerminoProyecto) {
        this.fechaTerminoProyecto = fechaTerminoProyecto;
    }

    public String getEstadoProyecto() {
        return estadoProyecto;
    }

    public void setEstadoProyecto(String estadoProyecto) {
        this.estadoProyecto = estadoProyecto;
    }

    public String getFotoProyecto() {
        return fotoProyecto;
    }

    public void setFotoProyecto(String fotoProyecto) {
        this.fotoProyecto = fotoProyecto;
    }
}
