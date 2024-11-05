package com.PoloDeSalud.UBB.service;

import com.PoloDeSalud.UBB.model.Colaborador;
import com.PoloDeSalud.UBB.repository.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ColaboradorServiceImpl implements ColaboradorService {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    //HU-01:Como administrador quiero saber cuales son los colaboradores que tengo en el Polo de Salud
    @Override
    public List<Colaborador> obtenerTodos() {
        return colaboradorRepository.findAll();
    }
    //H1: Listar Colaboradores busca un colaborador específico en la base de datos usando su ID.
    @Override
    public Colaborador obtenerPorId(Integer id) {
        Optional<Colaborador> colaborador = colaboradorRepository.findById(id);
        return colaborador.orElse(null);
    }
    //guarda un nuevo colaborador o actualiza uno existente en la base de datos.
    @Override
    public Colaborador guardar(Colaborador colaborador) {
        return colaboradorRepository.save(colaborador);
    }
    //elimina un colaborador específico de la base de datos usando su ID.
    @Override
    public void eliminar(Integer id) {
        colaboradorRepository.deleteById(id);
    }


    //S eagrean colaboradores autenticando su correo y contraseña
    @Override
    public Colaborador autenticar(String correo, String contrasena) {
        Optional<Colaborador> colaborador = colaboradorRepository.findByCorreoColaborador(correo);

        if (colaborador.isPresent() && colaborador.get().getContrasenaColaborador().equals(contrasena)) {
            return colaborador.get();
        } else {
            return null; // Retorna null si la autenticación falla
        }
    }
}
