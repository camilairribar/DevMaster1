package com.PoloDeSalud.UBB.service;

import com.PoloDeSalud.UBB.model.Colaborador;
import com.PoloDeSalud.UBB.repository.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColaboradorServicelmpl implements ColaboradorService {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Override
    public List<Colaborador> obtenerTodos() {
        return colaboradorRepository.findAll();
    }

    @Override
    public Colaborador obtenerPorId(Integer id) {
        Optional<Colaborador> colaborador = colaboradorRepository.findById(id);
        return colaborador.orElse(null);
    }

    @Override
    public Colaborador guardar(Colaborador colaborador) {
        return colaboradorRepository.save(colaborador);
    }

    @Override
    public void eliminar(Integer id) {
        colaboradorRepository.deleteById(id);
    }
}
