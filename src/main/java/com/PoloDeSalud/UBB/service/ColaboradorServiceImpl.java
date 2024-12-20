package com.PoloDeSalud.UBB.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PoloDeSalud.UBB.model.Colaborador;
import com.PoloDeSalud.UBB.repository.ColaboradorRepository;

@Service
public class ColaboradorServiceImpl implements ColaboradorService {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    //HU-01:Como administrador quiero saber cuales son los colaboradores que tengo en el Polo de Salud
    @Override
    public List<Colaborador> obtenerTodos() {
        return colaboradorRepository.findAll();
    }

    @Override
    public Colaborador obtenerPorId(Integer id) {
        Optional<Colaborador> colaborador = colaboradorRepository.findById(id);
        return colaborador.orElse(null);
    }

    @Override
    public Colaborador guardar(Colaborador colaborador) {
        return colaboradorRepository.save(colaborador);
    }

    @Override
    public void eliminar(Integer id) {
        colaboradorRepository.deleteById(id);
    }
    //HU-12: Colaborador Inicio de sesión
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
