package com.PoloDeSalud.UBB.service;

import com.PoloDeSalud.UBB.model.Noticia;
import java.util.List;
import java.util.Date;

public interface NoticiaService {
    List<Noticia> obtenerTodas();
    Noticia obtenerPorId(int id);
    Noticia guardar(Noticia noticia);
    void eliminar(int id);
    List<Noticia> buscarPorTitulo(String titulo);
    List<Noticia> buscarPorFecha(Date start, Date end);
}

