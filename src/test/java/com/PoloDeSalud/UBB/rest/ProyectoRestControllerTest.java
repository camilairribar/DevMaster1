package com.PoloDeSalud.UBB.rest;

import com.PoloDeSalud.UBB.model.Proyecto;
import com.PoloDeSalud.UBB.service.ProyectoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ProyectoRestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProyectoService proyectoService;

    @InjectMocks
    private ProyectoController proyectoController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(proyectoController).build();
    }

    @Test
    void obtenerTodos_DeberiaRetornarListaDeProyectos() throws Exception {
        // Arrange
        List<Proyecto> proyectos = getListaProyectos();
        when(proyectoService.obtenerTodos()).thenReturn(proyectos);

        // Act & Assert
        mockMvc.perform(get("/proyectos/ListaProyecto"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(proyectos.size())))
                .andExpect(jsonPath("$[0].idProyecto", is(proyectos.get(0).getIdProyecto())))
                .andExpect(jsonPath("$[0].nombre", is(proyectos.get(0).getNombreProyecto())))
                .andExpect(jsonPath("$[1].idProyecto", is(proyectos.get(1).getIdProyecto())))
                .andExpect(jsonPath("$[1].nombre", is(proyectos.get(1).getNombreProyecto())));
    }

    @Test
    void obtenerPorId_CuandoExiste_DeberiaRetornarProyecto() throws Exception {
        // Arrange
        Proyecto proyecto = getProyecto();
        when(proyectoService.obtenerPorId(1)).thenReturn(proyecto);

        // Act & Assert
        mockMvc.perform(get("/proyectos/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idProyecto", is(proyecto.getIdProyecto())))
                .andExpect(jsonPath("$.nombre", is(proyecto.getNombreProyecto())))
                .andExpect(jsonPath("$.descripcion", is(proyecto.getDescripcionProyecto())))
                .andExpect(jsonPath("$.estado", is(proyecto.getEstadoProyecto())));
    }

    @Test
    void crearProyecto_DeberiaCrearProyecto() throws Exception {
        // Arrange
        Proyecto proyecto = getProyecto();
        when(proyectoService.guardar(any(Proyecto.class))).thenReturn(proyecto);

        // Act & Assert
        mockMvc.perform(post("/proyectos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(proyecto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idProyecto", is(proyecto.getIdProyecto())))
                .andExpect(jsonPath("$.nombre", is(proyecto.getNombreProyecto())));
    }

    @Test
    void eliminarProyecto_DeberiaEliminarProyecto() throws Exception {
        // Arrange
        int id = 1;
        doNothing().when(proyectoService).eliminar(id);

        // Act & Assert
        mockMvc.perform(delete("/proyectos/{id}", id))
                .andExpect(status().isOk());
    }

    @Test
    void actualizarProyecto_DeberiaActualizarProyecto() throws Exception {
        // Arrange
        Proyecto proyecto = getProyecto();
        proyecto.setNombreProyecto("Nuevo Nombre");
        when(proyectoService.actualizarProyecto(anyInt(), any(Proyecto.class))).thenReturn(proyecto);

        // Act & Assert
        mockMvc.perform(put("/proyectos/{id}", proyecto.getIdProyecto())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(proyecto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("Nuevo Nombre")));
    }

    @Test
    void buscarPorNombre_DeberiaRetornarListaDeProyectos() throws Exception {
        // Arrange
        String nombre = "Proyecto 1";
        List<Proyecto> proyectos = getListaProyectos();
        when(proyectoService.buscarPorNombre(nombre)).thenReturn(proyectos);

        // Act & Assert
        mockMvc.perform(get("/proyectos/buscar")
                        .param("nombre", nombre))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(proyectos.size())))
                .andExpect(jsonPath("$[0].nombre", is(proyectos.get(0).getNombreProyecto())));
    }

    // Métodos auxiliares para datos de prueba
    private Proyecto getProyecto() {
        Proyecto proyecto = new Proyecto();
        proyecto.setIdProyecto(1);
        proyecto.setNombreProyecto("Proyecto 1");
        proyecto.setDescripcionProyecto("Descripción del proyecto 1");
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
        otroProyecto.setNombreProyecto("Proyecto 2");
        otroProyecto.setDescripcionProyecto("Descripción del proyecto 2");
        otroProyecto.setFechaPublicacionProyecto(new Date());
        otroProyecto.setFechaTerminoProyecto(new Date());
        otroProyecto.setEstadoProyecto("Inactivo");
        otroProyecto.setFotoProyecto("foto2.jpg");

        proyectos.add(otroProyecto);
        return proyectos;
    }
}


