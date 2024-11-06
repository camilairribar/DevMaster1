package com.PoloDeSalud.UBB.repository;

import com.PoloDeSalud.UBB.model.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProyectoRepository extends JpaRepository<Proyecto, Integer> {
}
