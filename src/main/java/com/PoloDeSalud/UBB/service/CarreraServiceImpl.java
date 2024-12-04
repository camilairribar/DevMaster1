package com.PoloDeSalud.UBB.service;

import com.PoloDeSalud.UBB.model.Carrera;
import com.PoloDeSalud.UBB.repository.CarreraRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarreraServiceImpl implements CarreraService {

    @Autowired
    CarreraRepository carreraRepository;

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

    //Nuevos metodos
    public Carrera actualizar(Carrera carrera) {
        if (carreraRepository.existsById(carrera.getIdCarreras())) {
            return carreraRepository.save(carrera);
        } else {
            throw new EntityNotFoundException("La carrera con ID " + carrera.getIdCarreras() + " no existe.");
        }
    }
    public List<Carrera> buscarPorNombre(String nombre) {
        return carreraRepository.findByNombreCarreraContainingIgnoreCase(nombre);
    }

    @Override
    public List<Carrera> obtenerTodasOrdenadas() {
        return carreraRepository.findAllByOrderByNombreCarreraAsc();
    }
}
