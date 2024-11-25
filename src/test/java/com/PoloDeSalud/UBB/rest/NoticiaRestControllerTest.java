package com.PoloDeSalud.UBB.rest;

import com.PoloDeSalud.UBB.model.Noticia;
import com.PoloDeSalud.UBB.service.NoticiaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class NoticiaRestControllerTest {

    @Mock
    private NoticiaService noticiaService;

    @InjectMocks
    private NoticiaController noticiaController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(noticiaController).build();
    }

    @Test
    void testObtenerTodas() throws Exception {
        List<Noticia> noticias = Arrays.asList(
                new Noticia(1, "Título 1", "Contenido 1","Foto", new Date()),
                new Noticia(2, "Título 2", "Contenido 2","Foto", new Date())
        );

        when(noticiaService.obtenerTodas()).thenReturn(noticias);

        mockMvc.perform(get("/noticias")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(noticias.size()))
                .andExpect(jsonPath("$[0].titulo").value("Título 1"));
    }

    @Test
    void testObtenerPorId() throws Exception {
        Noticia noticia = new Noticia(1, "Título 1", "Contenido 1","Foto", new Date());

        when(noticiaService.obtenerPorId(1)).thenReturn(noticia);

        mockMvc.perform(get("/noticias/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Título 1"));
    }

    @Test
    void testCrearNoticia() throws Exception {
        Noticia noticia = new Noticia(1, "Título Nuevo", "Contenido Nuevo","Foto", new Date());
        when(noticiaService.guardar(any(Noticia.class))).thenReturn(noticia);

        String nuevaNoticiaJson = "{\"titulo\":\"Título Nuevo\",\"contenido\":\"Contenido Nuevo\",\"fecha\":\"2024-11-23T00:00:00.000+00:00\"}";

        mockMvc.perform(post("/noticias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(nuevaNoticiaJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Título Nuevo"));
    }

    @Test
    void testEliminarNoticia() throws Exception {
        doNothing().when(noticiaService).eliminar(1);

        mockMvc.perform(delete("/noticias/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(noticiaService, times(1)).eliminar(1);
    }

    @Test
    void testBuscarPorTitulo() throws Exception {
        List<Noticia> noticias = Arrays.asList(
                new Noticia(1, "Título Buscado", "Contenido 1","Foto", new Date())
        );

        when(noticiaService.buscarPorTitulo("Buscado")).thenReturn(noticias);

        mockMvc.perform(get("/noticias/buscar/titulo")
                        .param("titulo", "Buscado")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].titulo").value("Título Buscado"));
    }

    @Test
    void testBuscarPorFecha() throws Exception {
        String start = "2023-11-01"; // Formato correcto
        String end = "2023-11-24";

        List<Noticia> noticias = Arrays.asList(
                new Noticia(1, "Título Fecha", "Contenido 1", null, new SimpleDateFormat("yyyy-MM-dd").parse("2023-11-05"))
        );

        when(noticiaService.buscarPorFecha(any(Date.class), any(Date.class))).thenReturn(noticias);

        mockMvc.perform(get("/noticias/buscar/fecha")
                        .param("start", start)
                        .param("end", end)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].titulo").value("Título Fecha"));
    }
}












