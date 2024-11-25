package com.PoloDeSalud.UBB.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @ManyToMany(mappedBy = "noticias")
    @JsonIgnore

    private List<Autor> autores = new ArrayList();
    //Constructor vacío
    public Noticia() {
    }
    //Constructor con parámetros
    public Noticia(int idNoticia, String tituloNoticia, String descripcionNoticia, String fotoNoticia, Date fechaPublicacionNoticia) {
        this.idNoticia = idNoticia;
        this.tituloNoticia = tituloNoticia;
        this.descripcionNoticia = descripcionNoticia;
        this.fotoNoticia = fotoNoticia;
        this.fechaPublicacionNoticia = fechaPublicacionNoticia;
    }
    // Getters and Setters
    public int getIdNoticia() {return idNoticia;}

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
    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

}

