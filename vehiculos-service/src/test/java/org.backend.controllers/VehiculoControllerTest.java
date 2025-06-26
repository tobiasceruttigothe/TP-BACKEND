package org.backend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.backend.DTOS.PosicionDTOCreate;
import org.backend.Services.VehiculoService;
import org.backend.controller.VehiculoController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VehiculoController.class)
public class VehiculoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VehiculoService vehiculoService;

    @Autowired
    private ObjectMapper objectMapper;

    //verifica que el controlador reciba correctamente un POST y delegue al servicio.
    @Test
    void testProcesarNuevaPosicion() throws Exception {
        PosicionDTOCreate posicion = new PosicionDTOCreate();
        posicion.setLatitud(-31.4167);
        posicion.setLongitud(-64.1833);
        posicion.setIdVehiculo(1L);

        mockMvc.perform(post("/api/vehiculos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(posicion)))
                .andExpect(status().isOk());

        verify(vehiculoService).procesarNuevaPosicion(posicion);
    }

    @Test
    void testProcesarNuevaPosicionSinIdVehiculo() throws Exception {
        PosicionDTOCreate posicion = new PosicionDTOCreate();
        posicion.setLatitud(-31.4167);
        posicion.setLongitud(-64.1833);
        // no seteamos el ID del vehículo

        mockMvc.perform(post("/api/vehiculos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(posicion)))
                .andExpect(status().is4xxClientError()); // o is4xxClientError si no tenés validación
    }
}
