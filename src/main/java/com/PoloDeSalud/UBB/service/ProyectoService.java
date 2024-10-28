package com.PoloDeSalud.UBB.service;

import com.PoloDeSalud.UBB.model.Proyecto;
import java.util.List;

public interface ProyectoService {
    List<Proyecto> obtenerTodos();
    Proyecto obtenerPorId(int id);
    Proyecto guardar(Proyecto proyecto);
    void eliminar(int id);
}
