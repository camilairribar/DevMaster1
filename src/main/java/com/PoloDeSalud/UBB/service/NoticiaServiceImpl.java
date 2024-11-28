package com.PoloDeSalud.UBB.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PoloDeSalud.UBB.model.Noticia;
import com.PoloDeSalud.UBB.repository.NoticiaRepository;

@Service
public class NoticiaServiceImpl implements NoticiaService {

    @Autowired
    private NoticiaRepository noticiaRepository;
    @Override
    public List<Noticia> obtenerTodas() {
        return noticiaRepository.findAll();
    }
    //HU-03:Como visitante del sitio, quiero ver el detalle de una noticia seleccionada para obtener información completa sobre ella
    @Override
    public Noticia obtenerPorId(int id) {
        Optional<Noticia> noticia = noticiaRepository.findById(id);
        return noticia.orElse(null);
    }
    //HU-02: Miembro del equipo de Polo de Salud que ingresa y actualiza noticias
    @Override
    public Noticia guardar(Noticia noticia) {
        return noticiaRepository.save(noticia);
    }
    @Override
    public void eliminar(int id) {
        noticiaRepository.deleteById(id);
    }

    //HU-07:Como visitante del sitio web, quiero filtrar las noticias por titulo o fecha para hacer una búsqueda más expedita
    @Override
    public List<Noticia> buscarPorTitulo(String titulo) {
        return noticiaRepository.findByTituloNoticiaContaining(titulo);
    }

    @Override
    public List<Noticia> buscarPorFecha(Date start, Date end) {
        return noticiaRepository.findByFechaPublicacionNoticiaBetween(start, end);
    }
}

