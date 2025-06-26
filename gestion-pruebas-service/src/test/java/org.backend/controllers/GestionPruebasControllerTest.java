package org.backend.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.backend.controller.PruebaController;
import org.backend.dtos.PruebaCreateDTO;
import org.backend.entities.Prueba;
import org.backend.services.PruebaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PruebaController.class)
public class GestionPruebasControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PruebaService pruebaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllPruebas() throws Exception {
        Prueba p1 = new Prueba();
        Prueba p2 = new Prueba();

        when(pruebaService.getAllPruebas()).thenReturn(List.of(p1, p2));

        mockMvc.perform(get("/api/pruebas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void testGetPruebasActivas() throws Exception {
        Prueba p1 = new Prueba();
        Prueba p2 = new Prueba();

        when(pruebaService.getPruebasActivas()).thenReturn(List.of(p1, p2));

        mockMvc.perform(get("/api/pruebas/activas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }
/*
    @Test
    void testAddPrueba() throws Exception {
        PruebaCreateDTO dto = new PruebaCreateDTO(1L, 2L, 3L);
        Prueba prueba = new Prueba();
        prueba.setFechaHoraInicio(LocalDateTime.now());
        prueba.setFechaHoraFin(LocalDateTime.now());

        when(pruebaService.savePrueba(any(PruebaCreateDTO.class))).thenReturn(prueba);

        mockMvc.perform(post("/api/pruebas/crear")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

 */

    @Test
    void testFinalizarPrueba_Success() throws Exception {
        Long id = 1L;
        String comentario = "Finalizada OK";

        Prueba prueba = new Prueba();
        when(pruebaService.getPruebaActivaById(id)).thenReturn(prueba);
        when(pruebaService.finalizarPrueba(eq(id), anyString())).thenReturn(prueba);

        mockMvc.perform(patch("/api/pruebas/finaliza/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comentario)))
                .andExpect(status().isOk());
    }

    @Test
    void testFinalizarPrueba_NotFound() throws Exception {
        Long id = 999L;
        when(pruebaService.getPruebaActivaById(id)).thenReturn(null);

        mockMvc.perform(patch("/api/pruebas/finaliza/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString("Comentario")))
                .andExpect(status().isNotFound());
    }

    // POST /api/pruebas/crear — error en servicio
    @Test
    void testAddPrueba_ErrorEnServicio() throws Exception {
        PruebaCreateDTO dto = new PruebaCreateDTO(1L, 2L, 3L);

        doThrow(new RuntimeException("Error interno")).when(pruebaService).savePrueba(any());

        mockMvc.perform(post("/api/pruebas/crear")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isInternalServerError());
    }

    // PATCH /api/pruebas/finaliza/{id} — excepción en servicio
    @Test
    void testFinalizarPrueba_ExcepcionEnServicio() throws Exception {
        Long id = 1L;
        String comentario = "Error";

        Prueba prueba = new Prueba();
        when(pruebaService.getPruebaActivaById(id)).thenReturn(prueba);
        doThrow(new RuntimeException("Falla")).when(pruebaService).finalizarPrueba(eq(id), anyString());

        mockMvc.perform(patch("/api/pruebas/finaliza/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comentario)))
                .andExpect(status().is5xxServerError());
    }

}
