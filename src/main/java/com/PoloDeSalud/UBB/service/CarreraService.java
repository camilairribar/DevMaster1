package com.PoloDeSalud.UBB.service;

import com.PoloDeSalud.UBB.model.Carrera;
import java.util.List;

public interface CarreraService {
    List<Carrera> obtenerTodas();
    Carrera obtenerPorId(int id);
    Carrera guardar(Carrera carrera);
    void eliminar(int id);
}
