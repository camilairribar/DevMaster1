package com.PoloDeSalud.UBB.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PoloDeSalud.UBB.model.Proyecto;
import com.PoloDeSalud.UBB.repository.ProyectoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProyectoServiceImpl implements ProyectoService {

    @Autowired
    ProyectoRepository proyectoRepository;

    @Override
    public List<Proyecto> obtenerTodos() {
        return proyectoRepository.findAll();
    }
    @Override
    public Proyecto obtenerPorId(int id) {
        Optional<Proyecto> proyecto = proyectoRepository.findById(id);
        return proyecto.orElse(null);
    }

    @Override
    public Proyecto guardar(Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    @Override
    public void eliminar(int id) {
        proyectoRepository.deleteById(id);
    }

    @Override
    public List<Proyecto> buscarPorNombre(String nombre) {
        return proyectoRepository.findByNombreProyectoContainingIgnoreCase(nombre);
    }
    @Override
    public Proyecto actualizarProyecto(int id, Proyecto proyectoDetalles) {
        Optional<Proyecto> proyectoExistente = proyectoRepository.findById(id);
        if (proyectoExistente.isPresent()) {
            Proyecto proyecto = proyectoExistente.get();
            proyecto.setNombreProyecto(proyectoDetalles.getNombreProyecto());
            proyecto.setDescripcionProyecto(proyectoDetalles.getDescripcionProyecto());
            proyecto.setFechaPublicacionProyecto(proyectoDetalles.getFechaPublicacionProyecto());
            proyecto.setFechaTerminoProyecto(proyectoDetalles.getFechaTerminoProyecto());
            proyecto.setEstadoProyecto(proyectoDetalles.getEstadoProyecto());
            proyecto.setFotoProyecto(proyectoDetalles.getFotoProyecto());
            return proyectoRepository.save(proyecto);
        }
        return null; // Retorna null si no existe el proyecto
    }
}
