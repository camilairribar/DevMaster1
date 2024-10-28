package com.PoloDeSalud.UBB.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "noticia_proyecto")
public class NoticiasProyectos {
    @Id
    @Column(name = "id_noticia")
    private int idNoticia;

    @Id
    @Column(name = "id_proyecto")
    private int idProyecto;

    // Getters and Setters
    public int getIdNoticia() {
        return idNoticia;
    }

    public void setIdNoticia(int idNoticia) {
        this.idNoticia = idNoticia;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }
}

