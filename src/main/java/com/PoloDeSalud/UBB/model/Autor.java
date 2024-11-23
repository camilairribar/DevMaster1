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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_autor")
    private int idAutor;

    @Column(name = "Nombre_autor", length = 100, nullable = false)
    @NotBlank(message = "El nombre no puede estar vacío")
    @JsonProperty("nombre")
    private String nombreAutor;

    @Column(name = "Correo_autor", length = 100, nullable = false, unique = true)
    @NotBlank(message = "El correo no puede estar vacío")
    @Email(message = "El correo debe tener un formato válido")
    @JsonProperty("correo")
    private String correoAutor;

    @ManyToMany
    @JoinTable(
            name = "autor_noticia",
            joinColumns = @JoinColumn(name = "id_autor"),
            inverseJoinColumns = @JoinColumn(name = "id_noticia")
    )
    @JsonIgnore
    private List<Noticia> noticias = new ArrayList<>();

    @ManyToMany(mappedBy = "autores") // Relación inversa
    @JsonIgnore // Evita ciclos en serialización JSON
    private List<Proyecto> proyectos = new ArrayList<>();

    // Constructor vacío necesario para JPA
    public Autor() {}

    // Constructor con parámetros
    public Autor(int idAutor, String nombreAutor, String correoAutor) {
        this.idAutor = idAutor;
        this.nombreAutor = nombreAutor;
        this.correoAutor = correoAutor;
    }
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

    public List<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(List<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "idAutor=" + idAutor +
                ", nombreAutor='" + nombreAutor + '\'' +
                ", correoAutor='" + correoAutor + '\'' +
                '}';
    }
}
