package com.PoloDeSalud.UBB.service;

import com.PoloDeSalud.UBB.model.Colaborador;
import java.util.List;

public interface ColaboradorService {
    List<Colaborador> obtenerTodos();

    Colaborador obtenerPorId(Integer id);

    Colaborador guardar(Colaborador colaborador);

    void eliminar(Integer id);

    Colaborador autenticar(String correo, String contrasena);
}
