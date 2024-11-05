package com.PoloDeSalud.UBB.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "noticia")
public class Noticia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_noticia")
    private int idNoticia;

    @Column(name = "Titulo_noticia", length = 100)
    @JsonProperty("titulo")
    private String tituloNoticia;

    @Column(name = "Descripcion_noticia", columnDefinition = "TEXT")
    @JsonProperty("contenido")
    private String descripcionNoticia;

    @Column(name = "Foto_noticia", length = 255)
    @JsonProperty("foto")
    private String fotoNoticia;

    @Column(name = "Fecha_publicacion_noticia")
    @JsonProperty("fechaPublicacion")
    private Date fechaPublicacionNoticia;

    // Getters and Setters
    public int getIdNoticia() {
        return idNoticia;
    }

    public void setIdNoticia(int idNoticia) {
        this.idNoticia = idNoticia;
    }

    public String getTituloNoticia() {
        return tituloNoticia;
    }

    public void setTituloNoticia(String tituloNoticia) {
        this.tituloNoticia = tituloNoticia;
    }

    public String getDescripcionNoticia() {
        return descripcionNoticia;
    }

    public void setDescripcionNoticia(String descripcionNoticia) {
        this.descripcionNoticia = descripcionNoticia;
    }

    public String getFotoNoticia() {
        return fotoNoticia;
    }

    public void setFotoNoticia(String fotoNoticia) {
        this.fotoNoticia = fotoNoticia;
    }

    public Date getFechaPublicacionNoticia() {
        return fechaPublicacionNoticia;
    }

    public void setFechaPublicacionNoticia(Date fechaPublicacionNoticia) {
        this.fechaPublicacionNoticia = fechaPublicacionNoticia;
    }
}

