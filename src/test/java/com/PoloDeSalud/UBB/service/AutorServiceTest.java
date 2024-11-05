package com.PoloDeSalud.UBB.service;

import com.PoloDeSalud.UBB.model.Autor;
import com.PoloDeSalud.UBB.model.Noticia;
import com.PoloDeSalud.UBB.repository.AutorRepository;
import com.PoloDeSalud.UBB.repository.NoticiaRepository;
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
public class AutorServiceTest {
    @Mock
    private AutorRepository autorRepository;

    @Mock
    private NoticiaRepository noticiaRepository;

    @InjectMocks
    private AutorServiceImpl autorService;

    @Test
    void obtenerTodos_DeberiaRetornarListaDeAutores() {
        // Arrange
        List<Autor> autoresMock = getListaAutores();
        when(autorRepository.findAll()).thenReturn(autoresMock);

        // Act
        List<Autor> resultado = autorService.obtenerTodos();

        // Assert
        assertNotNull(resultado);
        assertEquals(autoresMock.size(), resultado.size());
        assertEquals(autoresMock.get(0).getIdAutor(), resultado.get(0).getIdAutor());
    }

    @Test
    void obtenerPorId_CuandoExiste_DeberiaRetornarAutor() {
        // Arrange
        Autor autorMock = getAutor();
        when(autorRepository.findById(1)).thenReturn(Optional.of(autorMock));

        // Act
        Autor resultado = autorService.obtenerPorId(1);

        // Assert
        assertNotNull(resultado);
        assertEquals(autorMock.getIdAutor(), resultado.getIdAutor());
        assertEquals(autorMock.getNombreAutor(), resultado.getNombreAutor());
    }

    @Test
    void obtenerPorId_CuandoNoExiste_DeberiaRetornarNull() {
        // Arrange
        when(autorRepository.findById(1)).thenReturn(Optional.empty());

        // Act
        Autor resultado = autorService.obtenerPorId(1);

        // Assert
        assertNull(resultado);
    }

    @Test
    void guardar_DeberiaGuardarYRetornarAutor() {
        // Arrange
        Autor autorAGuardar = getAutor();
        when(autorRepository.save(autorAGuardar)).thenReturn(autorAGuardar);

        // Act
        Autor resultado = autorService.guardar(autorAGuardar);

        // Assert
        assertNotNull(resultado);
        assertEquals(autorAGuardar.getIdAutor(), resultado.getIdAutor());
        assertEquals(autorAGuardar.getNombreAutor(), resultado.getNombreAutor());
        verify(autorRepository, times(1)).save(autorAGuardar);
    }

    @Test
    void eliminar_DeberiaEliminarAutorPorId() {
        // Arrange
        int idAutor = 1;
        doNothing().when(autorRepository).deleteById(idAutor);

        // Act
        autorService.eliminar(idAutor);

        // Assert
        verify(autorRepository, times(1)).deleteById(idAutor);
    }

    @Test
    void asociarNoticia_CuandoAutorYNoticiaExisten_DeberiaAsociarYRetornarAutor() {
        // Arrange
        Autor autorMock = getAutor();
        Noticia noticiaMock = getNoticia();
        when(autorRepository.findById(1)).thenReturn(Optional.of(autorMock));
        when(noticiaRepository.findById(2)).thenReturn(Optional.of(noticiaMock));
        when(autorRepository.save(autorMock)).thenReturn(autorMock);

        // Act
        Autor resultado = autorService.asociarNoticia(1, 2);

        // Assert
        assertNotNull(resultado);
        assertTrue(resultado.getNoticias().contains(noticiaMock));
        verify(autorRepository, times(1)).save(autorMock);
    }

    @Test
    void asociarNoticia_CuandoAutorONoticiaNoExiste_DeberiaLanzarExcepcion() {
        // Arrange
        when(autorRepository.findById(1)).thenReturn(Optional.empty());
        when(noticiaRepository.findById(2)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> autorService.asociarNoticia(1, 2));
    }

    // Métodos auxiliares para crear datos de prueba
    private Autor getAutor() {
        Autor autor = new Autor();
        autor.setIdAutor(1);
        autor.setNombreAutor("Autor de prueba");
        autor.setCorreoAutor("autor@ejemplo.com");
        autor.setNoticias(new ArrayList<>());
        return autor;
    }

    private Noticia getNoticia() {
        Noticia noticia = new Noticia();
        noticia.setIdNoticia(2);
        noticia.setTituloNoticia("Noticia de prueba");
        noticia.setDescripcionNoticia("Descripción de prueba");
        noticia.setFotoNoticia("foto.jpg");
        noticia.setFechaPublicacionNoticia(new java.util.Date());
        noticia.setAutores(new ArrayList<>());
        return noticia;
    }

    private List<Autor> getListaAutores() {
        List<Autor> autores = new ArrayList<>();
        autores.add(getAutor());

        Autor otroAutor = new Autor();
        otroAutor.setIdAutor(2);
        otroAutor.setNombreAutor("Otro Autor");
        otroAutor.setCorreoAutor("otroautor@ejemplo.com");
        otroAutor.setNoticias(new ArrayList<>());

        autores.add(otroAutor);
        return autores;
    }
}

