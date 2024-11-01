package com.PoloDeSalud.UBB.service;

import com.PoloDeSalud.UBB.model.Noticia;
import com.PoloDeSalud.UBB.repository.NoticiaRepository;
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
public class NoticiaServiceTest {

    @Mock
    private NoticiaRepository noticiaRepository;

    @InjectMocks
    private NoticiaServiceImpl noticiaService;

    @Test
    void obtenerTodas_DeberiaRetornarListaDeNoticias() {
        // Arrange
        List<Noticia> noticiasMock = getListaNoticias();
        when(noticiaRepository.findAll()).thenReturn(noticiasMock);

        // Act
        List<Noticia> resultado = noticiaService.obtenerTodas();

        // Assert
        assertNotNull(resultado);
        assertEquals(noticiasMock.size(), resultado.size());
        assertEquals(noticiasMock.get(0).getIdNoticia(), resultado.get(0).getIdNoticia());
    }

    @Test
    void obtenerPorId_CuandoExiste_DeberiaRetornarNoticia() {
        // Arrange
        Noticia noticiaMock = getNoticia();
        when(noticiaRepository.findById(1)).thenReturn(Optional.of(noticiaMock));

        // Act
        Noticia resultado = noticiaService.obtenerPorId(1);

        // Assert
        assertNotNull(resultado);
        assertEquals(noticiaMock.getIdNoticia(), resultado.getIdNoticia());
        assertEquals(noticiaMock.getTituloNoticia(), resultado.getTituloNoticia());
    }

    @Test
    void obtenerPorId_CuandoNoExiste_DeberiaRetornarNull() {
        // Arrange
        when(noticiaRepository.findById(1)).thenReturn(Optional.empty());

        // Act
        Noticia resultado = noticiaService.obtenerPorId(1);

        // Assert
        assertNull(resultado);
    }

    @Test
    void crearNoticia_DeberiaGuardarYRetornarNoticia() {
        // Arrange
        Noticia noticiaAGuardar = getNoticia();
        when(noticiaRepository.save(noticiaAGuardar)).thenReturn(noticiaAGuardar);

        // Act
        Noticia resultado = noticiaService.guardar(noticiaAGuardar);

        // Assert
        assertNotNull(resultado);
        assertEquals(noticiaAGuardar.getIdNoticia(), resultado.getIdNoticia());
        assertEquals(noticiaAGuardar.getTituloNoticia(), resultado.getTituloNoticia());
        verify(noticiaRepository, times(1)).save(noticiaAGuardar);
    }

    @Test
    void eliminarNoticia_DeberiaEliminarNoticiaPorId() {
        // Arrange
        int idNoticia = 1;
        doNothing().when(noticiaRepository).deleteById(idNoticia);

        // Act
        noticiaService.eliminar(idNoticia);

        // Assert
        verify(noticiaRepository, times(1)).deleteById(idNoticia);
    }

    // Métodos auxiliares para crear datos de prueba
    private Noticia getNoticia() {
        Noticia noticia = new Noticia();
        noticia.setIdNoticia(1);
        noticia.setTituloNoticia("Título de prueba");
        noticia.setDescripcionNoticia("Descripción de prueba");
        noticia.setFotoNoticia("foto.jpg");
        noticia.setFechaPublicacionNoticia(new Date());
        return noticia;
    }

    private List<Noticia> getListaNoticias() {
        List<Noticia> noticias = new ArrayList<>();
        noticias.add(getNoticia());

        Noticia otraNoticia = new Noticia();
        otraNoticia.setIdNoticia(2);
        otraNoticia.setTituloNoticia("Otro título de prueba");
        otraNoticia.setDescripcionNoticia("Otra descripción de prueba");
        otraNoticia.setFotoNoticia("foto2.jpg");
        otraNoticia.setFechaPublicacionNoticia(new Date());

        noticias.add(otraNoticia);
        return noticias;
    }
}