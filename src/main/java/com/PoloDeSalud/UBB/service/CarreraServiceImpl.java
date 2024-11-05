package com.PoloDeSalud.UBB.service;

import com.PoloDeSalud.UBB.model.Carrera;
import com.PoloDeSalud.UBB.repository.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarreraServiceImpl implements CarreraService {

    @Autowired
    private CarreraRepository carreraRepository;

    @Override
    public List<Carrera> obtenerTodas() {
        return carreraRepository.findAll();
    }

    @Override
    public Carrera obtenerPorId(int id) {
        Optional<Carrera> carrera = carreraRepository.findById(id);
        return carrera.orElse(null);
    }

    @Override
    public Carrera guardar(Carrera carrera) {
        return carreraRepository.save(carrera);
    }

    @Override
    public void eliminar(int id) {
        carreraRepository.deleteById(id);
    }
}
