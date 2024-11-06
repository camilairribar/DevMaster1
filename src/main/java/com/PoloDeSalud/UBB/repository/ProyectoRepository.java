package com.PoloDeSalud.UBB.repository;

import com.PoloDeSalud.UBB.model.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProyectoRepository extends JpaRepository<Proyecto, Integer> {
    List<Proyecto> findByTituloContainingIgnoreCase(String titulo);
    // Nuevo método para obtener todos los proyectos ordenados por fecha de inicio ascendente
    List<Proyecto> findAllByOrderByFechaInicioAsc();

    // Nuevo método para obtener todos los proyectos ordenados por relevancia descendente
    List<Proyecto> findAllByOrderByRelevanciaDesc();
}
