package com.PoloDeSalud.UBB.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "autor_noticia")
public class AutorNoticia {
    @Id
    @Column(name = "id_autor")
    private int idAutor;

    @Id
    @Column(name = "id_noticia")
    private int idNoticia;

    // Getters and Setters
    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public int getIdNoticia() {
        return idNoticia;
    }

    public void setIdNoticia(int idNoticia) {
        this.idNoticia = idNoticia;
    }
}

