package com.PoloDeSalud.UBB.repository;

import com.PoloDeSalud.UBB.model.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticiaRepository extends JpaRepository<Noticia, Integer> {
}

