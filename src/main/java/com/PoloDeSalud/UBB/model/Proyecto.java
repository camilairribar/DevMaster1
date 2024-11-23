package com.PoloDeSalud.UBB.model;

import java.util.ArrayList;
import java.util.Date;
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

    @ManyToMany(mappedBy = "proyectos")
    @JsonIgnore
    private List<Colaborador> colaboradores = new ArrayList();

    @ManyToMany
    @JoinTable(
        name = "proyecto_autor", // Nombre de la tabla intermedia
        joinColumns = @JoinColumn(name = "id_proyecto"), // Columna que referencia a Proyecto
        inverseJoinColumns = @JoinColumn(name = "id_autor") // Columna que referencia a Autor
    )
    @JsonIgnore // Evita ciclos en serialización JSON
    private List<Autor> autores = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "proyecto_carrera", // Nombre de la tabla intermedia
        joinColumns = @JoinColumn(name = "id_proyecto"), // Columna que referencia a Proyecto
        inverseJoinColumns = @JoinColumn(name = "id_carreras") // Columna que referencia a Carrera
    )
    @JsonIgnore // Evita ciclos durante la serialización JSON
    private List<Carrera> carreras = new ArrayList<>();

    @ManyToMany(mappedBy = "proyectos")
    @JsonIgnore // Evita ciclos durante la serialización JSON
    private List<Noticia> noticias = new ArrayList<>();

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
    
    public List<Colaborador> getColaboradores() {
    return colaboradores;
    }   

    public void setColaboradores(List<Colaborador> colaboradores) {
        this.colaboradores = colaboradores;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public List<Noticia> getNoticias() {
        return noticias;
    }

    public void setNoticias(List<Noticia> noticias) {
        this.noticias = noticias;
    }
    public List<Carrera> getCarreras() {
    return carreras;
    }

    public void setCarreras(List<Carrera> carreras) {
        this.carreras = carreras;
    }
}
