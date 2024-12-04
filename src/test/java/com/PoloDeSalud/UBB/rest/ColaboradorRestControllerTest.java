package com.PoloDeSalud.UBB.rest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.PoloDeSalud.UBB.model.Colaborador;
import com.PoloDeSalud.UBB.rest.ColaboradorController.ColaboradorLoginRequest;
import com.PoloDeSalud.UBB.service.ColaboradorService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ColaboradorRestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ColaboradorService colaboradorService;

    @InjectMocks
    private ColaboradorController colaboradorController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(colaboradorController).build();
    }

    @Test
    void obtenerTodosDebeDevolverListaDeColaboradores() throws Exception {
        // Datos simulados
        Colaborador colaborador1 = new Colaborador();
        colaborador1.setIdColaborador(1);
        colaborador1.setNombreColaborador("Juan");

        Colaborador colaborador2 = new Colaborador();
        colaborador2.setIdColaborador(2);
        colaborador2.setNombreColaborador("Ana");

        List<Colaborador> listaColaboradores = Arrays.asList(colaborador1, colaborador2);

        // Configura el mock del servicio
        when(colaboradorService.obtenerTodos()).thenReturn(listaColaboradores);

        // Realiza la solicitud GET
        MockHttpServletResponse response = mockMvc.perform(get("/colaboradores/ListaColaborador")  // Ruta modificada
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        // Verifica el resultado
        assertEquals(200, response.getStatus());
        assertEquals(objectMapper.writeValueAsString(listaColaboradores), response.getContentAsString());
    }

    @Test
    void obtenerPorIdDebeDevolverColaboradorSiExiste() throws Exception {
        // Dato simulado
        Colaborador colaborador = new Colaborador();
        colaborador.setIdColaborador(1);
        colaborador.setNombreColaborador("Juan");

        // Configura el mock del servicio
        when(colaboradorService.obtenerPorId(1)).thenReturn(colaborador);

        // Realiza la solicitud GET
        MockHttpServletResponse response = mockMvc.perform(get("/colaboradores/{id}", 1)  // Ruta modificada
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        // Verifica el resultado
        assertEquals(200, response.getStatus());
        assertEquals(objectMapper.writeValueAsString(colaborador), response.getContentAsString());
    }

    @Test
    void crearColaboradorDebeDevolverColaboradorCreado() throws Exception {
        // Dato simulado
        Colaborador colaborador = new Colaborador();
        colaborador.setIdColaborador(1);
        colaborador.setNombreColaborador("Juan");

        // Configura el mock del servicio
        when(colaboradorService.guardar(any(Colaborador.class))).thenReturn(colaborador);

        // Realiza la solicitud POST
        MockHttpServletResponse response = mockMvc.perform(post("/colaboradores/CrearColaborador")  // Ruta modificada
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(colaborador)))
                .andReturn()
                .getResponse();

        // Verifica el resultado
        assertEquals(201, response.getStatus());  // Verifica que el código de estado sea 201 (CREATED)
        assertEquals(objectMapper.writeValueAsString(colaborador), response.getContentAsString());
    }

    @Test
    void eliminarColaboradorDebeDevolverNoContent() throws Exception {
        // Configura el mock del servicio
        doNothing().when(colaboradorService).eliminar(1);

        // Realiza la solicitud DELETE
        MockHttpServletResponse response = mockMvc.perform(delete("/colaboradores/EliminarColaborador/{id}", 1)  // Ruta modificada
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        // Verifica el resultado
        assertEquals(204, response.getStatus());
    }

    @Test
    void loginDebeAutenticarCorrectamente() throws Exception {
        // Dato simulado
        ColaboradorLoginRequest loginRequest = new ColaboradorLoginRequest();
        loginRequest.setCorreo("matias@gmail.com");
        loginRequest.setContrasena("123");

        Colaborador colaborador = new Colaborador();
        colaborador.setIdColaborador(1);
        colaborador.setNombreColaborador("Matias");

        // Configura el mock del servicio de autenticación
        when(colaboradorService.autenticar(loginRequest.getCorreo(), loginRequest.getContrasena())).thenReturn(colaborador);

        // Realiza la solicitud POST para login
        MockHttpServletResponse response = mockMvc.perform(post("/colaboradores/login")  // Ruta modificada
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andReturn()
                .getResponse();

        // Verifica el resultado
        assertEquals(200, response.getStatus());
        assertEquals(objectMapper.writeValueAsString(colaborador), response.getContentAsString());
    }
}