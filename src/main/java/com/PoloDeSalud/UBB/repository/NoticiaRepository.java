package com.PoloDeSalud.UBB.repository;

import com.PoloDeSalud.UBB.model.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

public interface NoticiaRepository extends JpaRepository<Noticia, Integer> {
    // Buscar noticias por título
    List<Noticia> findByTituloNoticiaContaining(String titulo);

    // Buscar noticias entre fechas
    @Query("SELECT n FROM Noticia n WHERE n.fechaPublicacionNoticia BETWEEN :start AND :end")
    List<Noticia> findByFechaPublicacionNoticiaBetween(Date start, Date end);
}

