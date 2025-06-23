package org.backend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.backend.controller.NotificacionesController;
import org.backend.dtos.NotificacionesCreate;
import org.backend.entities.Empleado;
import org.backend.entities.Interesado;
import org.backend.entities.Promocion;
import org.backend.entities.Vehiculo;
import org.backend.services.DsNotificacionService;
import org.backend.services.NotificacionesService;
import org.backend.services.PromocionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NotificacionesController.class)
//hay muchas clases con la anotacion @SpringBootApplication por eso limitamos la busqueda y le decimos a spring que solo use notificacionesController
@ContextConfiguration(classes = NotificacionesController.class)
public class NotificacionesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    //como spring solo carga los beans necesarios para el controller, no hay una instancia de dsnotif entonces mockeamos esa clase
    @MockBean
    private DsNotificacionService dsNotificacionService;

    //se encarga de guardar notificaciones relacionadas con incidentes
    @MockBean
    private NotificacionesService notificacionesService;


    @MockBean
    private PromocionService promocionService;

    @Autowired
    private ObjectMapper objectMapper;

    // chequeamos el siguiente endpoint POST /api/notificaciones/incidentes
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
    //chequeamos el sigueinte endpoint GET /api/notificaciones/promociones
    @Test
    void testGetPromociones() throws Exception {
        Promocion promo1 = new Promocion();
        promo1.setDescripcion("20% descuento");

        Promocion promo2 = new Promocion();
        promo2.setDescripcion("cuotas sin interés");

        when(promocionService.findAll()).thenReturn(List.of(promo1, promo2));

        mockMvc.perform(get("/api/notificaciones/promociones")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].descripcion").value("20% descuento"))
                .andExpect(jsonPath("$[1].descripcion").value("cuotas sin interés"));

        verify(dsNotificacionService, times(2)).sendMessage(anyString());
        verify(promocionService, times(1)).findAll();
    }





}
