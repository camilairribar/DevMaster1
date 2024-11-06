package com.PoloDeSalud.UBB.repository;

import com.PoloDeSalud.UBB.model.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarreraRepository extends JpaRepository<Carrera, Integer> {
    List<Carrera> findByNombreCarreraContainingIgnoreCase(String nombreCarrera);
    List<Carrera> findAllByOrderByNombreCarreraAsc();
}
