package com.PoloDeSalud.UBB.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Proyecto_Autor")
public class ProyectoAutor {
    @Id
    @Column(name = "id_autor")
    private int idAutor;

    @Id
    @Column(name = "id_proyecto")
    private int idProyecto;

    // Getters and Setters
    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public int getIdProyecto() {
        return idProyecto;
    }


    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }
}
