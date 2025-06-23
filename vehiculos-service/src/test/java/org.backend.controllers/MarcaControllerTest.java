package org.backend.controllers;

import org.backend.Repository.MarcaRepository;
import org.backend.controller.MarcaController;
import org.backend.entities.Marca;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MarcaController.class)
@ContextConfiguration(classes = MarcaController.class)
public class MarcaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MarcaRepository marcaRepository;

    @Test
    void testGetAllMarcas() throws Exception {
        Marca marca1 = new Marca();
        marca1.setNombre("Toyota");

        Marca marca2 = new Marca();
        marca2.setNombre("Ford");

        when(marcaRepository.findAll()).thenReturn(List.of(marca1, marca2));

        mockMvc.perform(get("/api/marcas")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].nombre").value("Toyota"))
                .andExpect(jsonPath("$[1].nombre").value("Ford"));

        verify(marcaRepository, times(1)).findAll();
    }
}
