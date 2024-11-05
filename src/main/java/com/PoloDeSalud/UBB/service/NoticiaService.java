package com.PoloDeSalud.UBB.service;

import com.PoloDeSalud.UBB.model.Noticia;
import java.util.List;

public interface NoticiaService {
    List<Noticia> obtenerTodas();
    Noticia obtenerPorId(int id);
    Noticia guardar(Noticia noticia);
    void eliminar(int id);
}

