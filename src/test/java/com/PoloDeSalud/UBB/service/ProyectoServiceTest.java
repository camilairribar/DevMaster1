package com.PoloDeSalud.UBB.service;

import com.PoloDeSalud.UBB.model.Proyecto;
import com.PoloDeSalud.UBB.repository.ProyectoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProyectoServiceTest {

    @Mock
    private ProyectoRepository proyectoRepository;

    @InjectMocks
    private ProyectoServiceImpl proyectoService;

    @Test
    void obtenerTodos_DeberiaRetornarListaDeProyectos() {
        // Arrange
        List<Proyecto> proyectosMock = getListaProyectos();
        when(proyectoRepository.findAll()).thenReturn(proyectosMock);

        // Act
        List<Proyecto> resultado = proyectoService.obtenerTodos();

        // Assert
        assertNotNull(resultado);
        assertEquals(proyectosMock.size(), resultado.size());
        assertEquals(proyectosMock.get(0).getIdProyecto(), resultado.get(0).getIdProyecto());
    }

    @Test
    void obtenerPorId_CuandoExiste_DeberiaRetornarProyecto() {
        // Arrange
        Proyecto proyectoMock = getProyecto();
        when(proyectoRepository.findById(1)).thenReturn(Optional.of(proyectoMock));

        // Act
        Proyecto resultado = proyectoService.obtenerPorId(1);

        // Assert
        assertNotNull(resultado);
        assertEquals(proyectoMock.getIdProyecto(), resultado.getIdProyecto());
        assertEquals(proyectoMock.getNombreProyecto(), resultado.getNombreProyecto());
    }

    @Test
    void obtenerPorId_CuandoNoExiste_DeberiaRetornarNull() {
        // Arrange
        when(proyectoRepository.findById(1)).thenReturn(Optional.empty());

        // Act
        Proyecto resultado = proyectoService.obtenerPorId(1);

        // Assert
        assertNull(resultado);
    }

    @Test
    void crearProyecto_DeberiaGuardarYRetornarProyecto() {
        // Arrange
        Proyecto proyectoAGuardar = getProyecto();
        when(proyectoRepository.save(proyectoAGuardar)).thenReturn(proyectoAGuardar);

        // Act
        Proyecto resultado = proyectoService.guardar(proyectoAGuardar);

        // Assert
        assertNotNull(resultado);
        assertEquals(proyectoAGuardar.getIdProyecto(), resultado.getIdProyecto());
        assertEquals(proyectoAGuardar.getNombreProyecto(), resultado.getNombreProyecto());
        verify(proyectoRepository, times(1)).save(proyectoAGuardar);
    }

    @Test
    void eliminarProyecto_DeberiaEliminarProyectoPorId() {
        // Arrange
        int idProyecto = 1;
        doNothing().when(proyectoRepository).deleteById(idProyecto);

        // Act
        proyectoService.eliminar(idProyecto);

        // Assert
        verify(proyectoRepository, times(1)).deleteById(idProyecto);
    }

    @Test
    void actualizarProyecto_CuandoExiste_DeberiaActualizarYRetornarProyecto() {
        // Arrange
        int idProyecto = 1;
        Proyecto proyectoExistente = getProyecto();
        Proyecto proyectoActualizado = new Proyecto();
        proyectoActualizado.setNombreProyecto("Nombre Actualizado");
        proyectoActualizado.setDescripcionProyecto("Descripción Actualizada");
        proyectoActualizado.setFechaPublicacionProyecto(new Date());
        proyectoActualizado.setFechaTerminoProyecto(new Date());
        proyectoActualizado.setEstadoProyecto("Completado");
        proyectoActualizado.setFotoProyecto("foto_actualizada.jpg");

        when(proyectoRepository.findById(idProyecto)).thenReturn(Optional.of(proyectoExistente));
        when(proyectoRepository.save(any(Proyecto.class))).thenReturn(proyectoActualizado);

        // Act
        Proyecto resultado = proyectoService.actualizarProyecto(idProyecto, proyectoActualizado);

        // Assert
        assertNotNull(resultado);
        assertEquals(proyectoActualizado.getNombreProyecto(), resultado.getNombreProyecto());
        assertEquals(proyectoActualizado.getDescripcionProyecto(), resultado.getDescripcionProyecto());
        verify(proyectoRepository, times(1)).findById(idProyecto);
        verify(proyectoRepository, times(1)).save(proyectoExistente);
    }

    @Test
    void actualizarProyecto_CuandoNoExiste_DeberiaRetornarNull() {
        // Arrange
        int idProyecto = 1;
        Proyecto proyectoActualizado = getProyecto();
        when(proyectoRepository.findById(idProyecto)).thenReturn(Optional.empty());

        // Act
        Proyecto resultado = proyectoService.actualizarProyecto(idProyecto, proyectoActualizado);

        // Assert
        assertNull(resultado);
        verify(proyectoRepository, times(1)).findById(idProyecto);
        verify(proyectoRepository, never()).save(any(Proyecto.class));
    }
    // Métodos auxiliares para crear datos de prueba
    private Proyecto getProyecto() {
        Proyecto proyecto = new Proyecto();
        proyecto.setIdProyecto(1);
        proyecto.setNombreProyecto("Proyecto de prueba");
        proyecto.setDescripcionProyecto("Descripción de prueba");
        proyecto.setFechaPublicacionProyecto(new Date());
        proyecto.setFechaTerminoProyecto(new Date());
        proyecto.setEstadoProyecto("Activo");
        proyecto.setFotoProyecto("foto.jpg");
        return proyecto;
    }

    private List<Proyecto> getListaProyectos() {
        List<Proyecto> proyectos = new ArrayList<>();
        proyectos.add(getProyecto());

        Proyecto otroProyecto = new Proyecto();
        otroProyecto.setIdProyecto(2);
        otroProyecto.setNombreProyecto("Otro Proyecto de prueba");
        otroProyecto.setDescripcionProyecto("Otra descripción de prueba");
        otroProyecto.setFechaPublicacionProyecto(new Date());
        otroProyecto.setFechaTerminoProyecto(new Date());
        otroProyecto.setEstadoProyecto("Completado");
        otroProyecto.setFotoProyecto("foto2.jpg");

        proyectos.add(otroProyecto);
        return proyectos;
    }
}
