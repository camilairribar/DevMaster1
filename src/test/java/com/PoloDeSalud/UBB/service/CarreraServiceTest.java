package com.PoloDeSalud.UBB.service;

import com.PoloDeSalud.UBB.model.Carrera;
import com.PoloDeSalud.UBB.repository.CarreraRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarreraServiceTest {

    @Mock
    private CarreraRepository carreraRepository;

    @InjectMocks
    private CarreraServiceImpl carreraService;

    @Test
    void obtenerTodas_DeberiaRetornarListaDeCarreras() {
        // Arrange
        List<Carrera> carrerasMock = getListaCarreras();
        when(carreraRepository.findAll()).thenReturn(carrerasMock);

        // Act
        List<Carrera> resultado = carreraService.obtenerTodas();

        // Assert
        assertNotNull(resultado);
        assertEquals(carrerasMock.size(), resultado.size());
        assertEquals(carrerasMock.get(0).getIdCarreras(), resultado.get(0).getIdCarreras());
    }

    @Test
    void obtenerPorId_CuandoExiste_DeberiaRetornarCarrera() {
        // Arrange
        Carrera carreraMock = getCarrera();
        when(carreraRepository.findById(1)).thenReturn(Optional.of(carreraMock));

        // Act
        Carrera resultado = carreraService.obtenerPorId(1);

        // Assert
        assertNotNull(resultado);
        assertEquals(carreraMock.getIdCarreras(), resultado.getIdCarreras());
        assertEquals(carreraMock.getNombreCarrera(), resultado.getNombreCarrera());
    }

    @Test
    void obtenerPorId_CuandoNoExiste_DeberiaRetornarNull() {
        // Arrange
        when(carreraRepository.findById(1)).thenReturn(Optional.empty());

        // Act
        Carrera resultado = carreraService.obtenerPorId(1);

        // Assert
        assertNull(resultado);
    }

    @Test
    void guardarCarrera_DeberiaGuardarYRetornarCarrera() {
        // Arrange
        Carrera carreraAGuardar = getCarrera();
        when(carreraRepository.save(carreraAGuardar)).thenReturn(carreraAGuardar);

        // Act
        Carrera resultado = carreraService.guardar(carreraAGuardar);

        // Assert
        assertNotNull(resultado);
        assertEquals(carreraAGuardar.getIdCarreras(), resultado.getIdCarreras());
        assertEquals(carreraAGuardar.getNombreCarrera(), resultado.getNombreCarrera());
        verify(carreraRepository, times(1)).save(carreraAGuardar);
    }

    @Test
    void eliminarCarrera_DeberiaEliminarCarreraPorId() {
        // Arrange
        int idCarrera = 1;
        doNothing().when(carreraRepository).deleteById(idCarrera);

        // Act
        carreraService.eliminar(idCarrera);

        // Assert
        verify(carreraRepository, times(1)).deleteById(idCarrera);
    }

    // Métodos auxiliares para crear datos de prueba
    private Carrera getCarrera() {
        Carrera carrera = new Carrera();
        carrera.setIdCarreras(1);
        carrera.setNombreCarrera("Ingeniería en Sistemas");
        carrera.setDescripcionCarrera("Descripción de Ingeniería en Sistemas");
        carrera.setFacultad("Facultad de Ingeniería");
        return carrera;
    }

    private List<Carrera> getListaCarreras() {
        List<Carrera> carreras = new ArrayList<>();
        carreras.add(getCarrera());

        Carrera otraCarrera = new Carrera();
        otraCarrera.setIdCarreras(2);
        otraCarrera.setNombreCarrera("Medicina");
        otraCarrera.setDescripcionCarrera("Descripción de Medicina");
        otraCarrera.setFacultad("Facultad de Medicina");

        carreras.add(otraCarrera);
        return carreras;
    }
}