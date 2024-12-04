package com.PoloDeSalud.UBB.rest;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.PoloDeSalud.UBB.model.Carrera;
import com.PoloDeSalud.UBB.service.CarreraService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CarreraController.class)
public class CarreraRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarreraService carreraService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void obtenerCarreras_DeberiaRetornarListaDeCarreras() throws Exception {
        // Arrange
        List<Carrera> carreras = getListaCarreras();
        when(carreraService.obtenerTodas()).thenReturn(carreras);

        // Act & Assert
        mockMvc.perform(get("/carreras/ListaCarrera"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(carreras.size())))
                .andExpect(jsonPath("$[0].idCarreras", is(carreras.get(0).getIdCarreras())));
    }

    @Test
    void obtenerPorId_CuandoExiste_DeberiaRetornarCarrera() throws Exception {
        // Arrange
        Carrera carrera = getCarrera();
        when(carreraService.obtenerPorId(1)).thenReturn(carrera);

        // Act & Assert
        mockMvc.perform(get("/carreras/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idCarreras", is(carrera.getIdCarreras())))
                .andExpect(jsonPath("$.nombre", is(carrera.getNombreCarrera())));
    }

    @Test
    void crearCarrera_DeberiaCrearCarrera() throws Exception {
        // Arrange
        Carrera carrera = getCarrera();
        when(carreraService.guardar(any(Carrera.class))).thenReturn(carrera);

        // Act & Assert
        mockMvc.perform(post("/carreras/CrearCarrera")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(carrera)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idCarreras", is(carrera.getIdCarreras())))
                .andExpect(jsonPath("$.nombre", is(carrera.getNombreCarrera())));
    }

    @Test
    void actualizarCarrera_CuandoExiste_DeberiaActualizarCarrera() throws Exception {
        // Arrange
        Carrera carrera = getCarrera();
        carrera.setNombreCarrera("Nuevo Nombre");
        when(carreraService.actualizar(any(Carrera.class))).thenReturn(carrera);

        // Act & Assert
        mockMvc.perform(put("/carreras/{id}", carrera.getIdCarreras())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(carrera)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("Nuevo Nombre")));
    }

    @Test
    void eliminarCarrera_DeberiaEliminarCarrera() throws Exception {
        // Arrange
        int id = 1;

        // Act & Assert
        mockMvc.perform(delete("/carreras/EliminarCarrera/{id}", id))
                .andExpect(status().isOk());
    }

    @Test
    void buscarPorNombre_DeberiaRetornarListaDeCarreras() throws Exception {
        // Arrange
        String nombre = "Ingeniería";
        List<Carrera> carreras = getListaCarreras();
        when(carreraService.buscarPorNombre(nombre)).thenReturn(carreras);

        // Act & Assert
        mockMvc.perform(get("/carreras/buscar")
                        .param("nombre", nombre))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(carreras.size())));
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