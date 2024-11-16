package com.PoloDeSalud.UBB.rest;

import com.PoloDeSalud.UBB.model.Autor;
import com.PoloDeSalud.UBB.service.AutorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AutorRestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AutorService autorService;

    @InjectMocks
    private AutorController autorRestController;

    @InjectMocks
    private AutorController autorController;


    private ObjectMapper objectMapper;
    @BeforeEach
    void setup() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(autorController).build();
    }

    public AutorRestControllerTest() {
        // Inicializa los mocks
        MockitoAnnotations.openMocks(this);
        // Configura MockMvc con el controlador que estás probando
        this.mockMvc = MockMvcBuilders.standaloneSetup(autorRestController).build();
    }

    @Test
    void crearAutorDebeDevolverAutorCreado() throws Exception {
        // Crea un autor simulado
        Autor autorMock = new Autor(1, "Matías", "matias@gmail.com");

        // Configura el comportamiento del mock del servicio
        when(autorService.guardar(autorMock)).thenReturn(autorMock);

        // Realiza la solicitud POST y verifica el resultado
        mockMvc.perform(post("/autores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\": \"Matías\", \"correo\": \"matias@gmail.com\"}"))
                .andExpect(status().isCreated());
    }


    @Test
    void obtenerTodosDebeDevolverListaDeAutores() throws Exception {
        // Given
        Autor autor1 = new Autor(1, "Autor1", "autor1@mail.com");
        Autor autor2 = new Autor(2, "Autor2", "autor2@mail.com");
        given(autorService.obtenerTodos()).willReturn(Arrays.asList(autor1, autor2));

        // When
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/autores")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        // Then
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(objectMapper.writeValueAsString(Arrays.asList(autor1, autor2)), response.getContentAsString());
    }

    @Test
    void obtenerPorIdDebeDevolverAutorSiExiste() throws Exception {
        // Given
        Autor autor = new Autor(1, "Autor1", "autor1@mail.com");
        given(autorService.obtenerPorId(1)).willReturn(autor);

        // When
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/autores/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        // Then
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(objectMapper.writeValueAsString(autor), response.getContentAsString());
    }
    @Test
    void eliminarAutorDebeDevolverStatusNoContent() throws Exception {
        // Given
        doNothing().when(autorService).eliminar(1);

        // When
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.delete("/autores/1"))
                .andReturn()
                .getResponse();

        // Then
        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
    }

    @Test
    void asociarNoticiaDebeDevolverAutorActualizadoSiExitoso() throws Exception {
        // Given
        Autor autor = new Autor(1, "Autor Actualizado", "autoractualizado@mail.com");
        given(autorService.asociarNoticia(1, 2)).willReturn(autor);

        // When
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/autores/1/noticias/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        // Then
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(objectMapper.writeValueAsString(autor), response.getContentAsString());
    }

    @Test
    void asociarNoticiaDebeDevolverNotFoundSiEntidadNoExiste() throws Exception {
        // Given
        given(autorService.asociarNoticia(1, 2)).willThrow(new EntityNotFoundException("Autor o Noticia no encontrada"));

        // When
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/autores/1/noticias/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        // Then
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        assertEquals("Autor o Noticia no encontrada", response.getContentAsString());
    }
}