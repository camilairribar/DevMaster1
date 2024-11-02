package com.PoloDeSalud.UBB.repository;

import com.PoloDeSalud.UBB.model.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Integer> {
    Optional<Colaborador> findByCorreoColaborador(String correoColaborador);
}
