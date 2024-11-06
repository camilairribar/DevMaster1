package com.PoloDeSalud.UBB.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autor")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_autor")
    private int idAutor;

    @Column(name = "Nombre_autor", length = 100)
    @JsonProperty("nombre")
    private String nombreAutor;

    @Column(name = "Correo_autor", length = 100)
    @JsonProperty("correo")
    private String correoAutor;

    @ManyToMany
    @JoinTable(
            name = "autor_noticia",
            joinColumns = @JoinColumn(name = "id_autor"),
            inverseJoinColumns = @JoinColumn(name = "id_noticia")
    )
    @JsonIgnore

    private List<Noticia> noticias;

    // Getters y Setters
    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public String getCorreoAutor() {
        return correoAutor;
    }

    public void setCorreoAutor(String correoAutor) {
        this.correoAutor = correoAutor;
    }

    public List<Noticia> getNoticias() {
        return noticias;
    }

    public void setNoticias(List<Noticia> noticias) {
        this.noticias = noticias;
    }

}
