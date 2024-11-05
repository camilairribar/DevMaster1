package com.PoloDeSalud.UBB.service;

import com.PoloDeSalud.UBB.model.Colaborador;
import com.PoloDeSalud.UBB.repository.ColaboradorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ColaboradorServiceTest {

    @Mock
    private ColaboradorRepository colaboradorRepository;

    @InjectMocks
    private ColaboradorServiceImpl colaboradorService;

    @Test
    void obtenerPorCorreoYContrasena_CuandoExiste_DeberiaRetornarColaborador() {
        // Arrange
        Colaborador colaboradorMock = getColaborador();
        when(colaboradorRepository.findByCorreoColaborador("matias@gmail.com"))
                .thenReturn(Optional.of(colaboradorMock));

        // Act
        Colaborador resultado = colaboradorService.autenticar("matias@gmail.com", "123");

        // Assert
        assertNotNull(resultado);
        assertEquals(colaboradorMock.getIdColaborador(), resultado.getIdColaborador());
        assertEquals(colaboradorMock.getCorreoColaborador(), resultado.getCorreoColaborador());
    }

    @Test
    void obtenerPorCorreoYContrasena_CuandoNoExiste_DeberiaRetornarNull() {
        // Arrange
        when(colaboradorRepository.findByCorreoColaborador("matias@gmail.com"))
                .thenReturn(Optional.empty());
        // Act
        Colaborador resultado = colaboradorService.autenticar("matias@gmail.com", "123");

        // Assert
        assertNull(resultado);
    }

    @Test
    void crearColaborador_DeberiaGuardarYRetornarColaborador() {
        // Arrange
        Colaborador colaboradorAGuardar = getColaborador();
        when(colaboradorRepository.save(colaboradorAGuardar)).thenReturn(colaboradorAGuardar);

        // Act
        Colaborador resultado = colaboradorService.guardar(colaboradorAGuardar);

        // Assert
        assertNotNull(resultado);
        assertEquals(colaboradorAGuardar.getIdColaborador(), resultado.getIdColaborador());
        assertEquals(colaboradorAGuardar.getCorreoColaborador(), resultado.getCorreoColaborador());
        verify(colaboradorRepository, times(1)).save(colaboradorAGuardar);
    }

    @Test
    void eliminarColaborador_DeberiaEliminarColaboradorPorId() {
        // Arrange
        int idColaborador = 1;
        doNothing().when(colaboradorRepository).deleteById(idColaborador);

        // Act
        colaboradorService.eliminar(idColaborador);

        // Assert
        verify(colaboradorRepository, times(1)).deleteById(idColaborador);
    }
    @Test
    void obtenerPorId_CuandoExiste_DeberiaRetornarColaborador() {
        // Arrange
        Colaborador colaboradorMock = getColaborador();
        when(colaboradorRepository.findById(1)).thenReturn(Optional.of(colaboradorMock));

        // Act
        Colaborador resultado = colaboradorService.obtenerPorId(1);

        // Assert
        assertNotNull(resultado);
        assertEquals(colaboradorMock.getIdColaborador(), resultado.getIdColaborador());
        assertEquals(colaboradorMock.getCorreoColaborador(), resultado.getCorreoColaborador());
    }

    @Test
    void obtenerPorId_CuandoNoExiste_DeberiaRetornarNull() {
        // Arrange
        when(colaboradorRepository.findById(1)).thenReturn(Optional.empty());

        // Act
        Colaborador resultado = colaboradorService.obtenerPorId(1);

        // Assert
        assertNull(resultado);
    }

    // Método auxiliar para crear un colaborador de prueba
    private Colaborador getColaborador() {
        Colaborador colaborador = new Colaborador();
        colaborador.setIdColaborador(1);
        colaborador.setNombreColaborador("matias");
        colaborador.setCorreoColaborador("matias@gmail.com");
        colaborador.setContrasenaColaborador("123");
        colaborador.setRol(true);
        return colaborador;
    }
}
