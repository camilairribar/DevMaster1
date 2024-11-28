package com.PoloDeSalud.UBB.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.PoloDeSalud.UBB.model.Noticia;

public interface NoticiaRepository extends JpaRepository<Noticia, Integer> {
    // Buscar noticias por título
    List<Noticia> findByTituloNoticiaContaining(String titulo);

    // Buscar noticias entre fechas
    @Query("SELECT n FROM Noticia n WHERE n.fechaPublicacionNoticia BETWEEN :start AND :end")
    List<Noticia> findByFechaPublicacionNoticiaBetween(Date start, Date end);
}

