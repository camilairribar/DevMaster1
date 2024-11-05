package com.PoloDeSalud.UBB.service;

import com.PoloDeSalud.UBB.model.Autor;
import java.util.List;

public interface AutorService {
    List<Autor> obtenerTodos();
    Autor obtenerPorId(Integer id);
    Autor guardar(Autor autor);
    void eliminar(Integer id);

    Autor asociarNoticia(int idAutor, int idNoticia);
}

