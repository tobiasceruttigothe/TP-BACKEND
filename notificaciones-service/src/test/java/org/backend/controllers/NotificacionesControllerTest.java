package org.backend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.backend.controller.NotificacionesController;
import org.backend.dtos.NotificacionesCreate;
import org.backend.entities.Empleado;
import org.backend.entities.Interesado;
import org.backend.entities.Vehiculo;
import org.backend.services.NotificacionesService;
import org.backend.services.PromocionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NotificacionesController.class)
public class NotificacionesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificacionesService notificacionesService;

    @MockBean
    private PromocionService promocionService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCrearNotificacion() throws Exception {
        Empleado empleado = new Empleado();
        Interesado interesado = new Interesado();
        Vehiculo vehiculo = new Vehiculo();
        String comentario = "Incidente registrado";

        NotificacionesCreate dto = new NotificacionesCreate(empleado, vehiculo, interesado, comentario);

        mockMvc.perform(post("/api/notificaciones/incidentes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());

        verify(notificacionesService, times(1)).saveNotification(any(NotificacionesCreate.class));
    }
}
