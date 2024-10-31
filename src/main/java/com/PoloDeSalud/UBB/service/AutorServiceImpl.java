package com.PoloDeSalud.UBB.service;

import com.PoloDeSalud.UBB.model.Autor;
import com.PoloDeSalud.UBB.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorServiceImpl implements AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @Override
    public List<Autor> obtenerTodos() {
        return autorRepository.findAll();
    }

    @Override
    public Autor obtenerPorId(Integer id) {
        Optional<Autor> autor = autorRepository.findById(id);
        return autor.orElse(null);
    }

    @Override
    public Autor guardar(Autor autor) {
        return autorRepository.save(autor);
    }

    @Override
    public void eliminar(Integer id) {
        autorRepository.deleteById(id);
    }
}

