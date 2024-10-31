package com.PoloDeSalud.UBB.service;

import com.PoloDeSalud.UBB.model.Noticia;
import com.PoloDeSalud.UBB.repository.NoticiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoticiaServiceImpl implements NoticiaService {

    @Autowired
    private NoticiaRepository noticiaRepository;

    @Override
    public List<Noticia> obtenerTodas() {
        return noticiaRepository.findAll();
    }

    @Override
    public Noticia obtenerPorId(Long id) {
        return null;
    }

    public Noticia obtenerPorId(int id) {
        Optional<Noticia> noticia = noticiaRepository.findById(id);
        return noticia.orElse(null);
    }

    @Override
    public Noticia guardar(Noticia noticia) {
        return noticiaRepository.save(noticia);
    }

    public void eliminar(int id) {
        noticiaRepository.deleteById(id);
    }
}

