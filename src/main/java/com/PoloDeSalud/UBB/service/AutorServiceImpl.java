package com.PoloDeSalud.UBB.service;

import com.PoloDeSalud.UBB.model.Autor;
import com.PoloDeSalud.UBB.model.Noticia;
import com.PoloDeSalud.UBB.repository.AutorRepository;
import com.PoloDeSalud.UBB.repository.NoticiaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;
import java.util.Optional;

@Service
public class AutorServiceImpl implements AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private NoticiaRepository noticiaRepository;

    //HU-27: Asociar un Autor con una Noticia
    public Autor asociarNoticia(int idAutor, int idNoticia) {
        Optional<Autor> autorOpt = autorRepository.findById(idAutor);
        Optional<Noticia> noticiaOpt = noticiaRepository.findById(idNoticia);

        if (autorOpt.isPresent() && noticiaOpt.isPresent()) {
            Autor autor = autorOpt.get();
            Noticia noticia = noticiaOpt.get();

            // Verifica si las listas están inicializadas
            if (autor.getNoticias() == null) {
                autor.setNoticias(new ArrayList<>());
            }
            if (noticia.getAutores() == null) {
                noticia.setAutores(new ArrayList<>());
            }

            // Asociar el autor con la noticia
            autor.getNoticias().add(noticia);
            noticia.getAutores().add(autor);

            // Guardar la asociación en la base de datos
            autorRepository.save(autor);
            return autor;
        } else {
            throw new EntityNotFoundException("Autor o Noticia no encontrados");
        }
    }


    @Override
    public List<Autor> obtenerTodos() {
        return autorRepository.findAll();
    }

    @Override
    public Autor obtenerPorId(Integer id) {
        Optional<Autor> autor = autorRepository.findById(id);
        return autor.orElse(null);
    }
    //HU-18: Registrar un Nuevo Autor
    @Override
    public Autor guardar(Autor autor) {
        return autorRepository.save(autor);
    }

    @Override
    public void eliminar(Integer id) {
        autorRepository.deleteById(id);
    }
}

