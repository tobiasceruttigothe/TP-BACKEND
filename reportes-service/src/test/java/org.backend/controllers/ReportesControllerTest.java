package org.backend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.backend.DTOS.ReporteDTO;
import org.backend.controller.ReportesController;
import org.backend.entities.Notificacion;
import org.backend.entities.Prueba;
import org.backend.services.NotificacionService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReportesController.class)
public class ReportesControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificacionService notificacionService;

    @MockBean
    private PruebaService pruebaService;

    @Test
    void testGetAllIncidentes() throws Exception {
        // creamos dos notificaciones
        Notificacion n1 = new Notificacion();
        n1.setComentario("Incidente 1");

        Notificacion n2 = new Notificacion();
        n2.setComentario("Incidente 2");

        //mockeamos el servicio notificacionesService para q devuelva las not creadas antes cuanod haga el get
        when(notificacionService.getNotificaciones()).thenReturn(List.of(n1, n2));

        //llamamos al endpoint y chequea si tira un 200, que el json tenga lo q corresponde, los comentarios coincidan y que se llame al metodo del servicio
        mockMvc.perform(get("/api/reportes/incidentes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].comentario").value("Incidente 1"))
                .andExpect(jsonPath("$[1].comentario").value("Incidente 2"));

        verify(notificacionService, times(1)).getNotificaciones();
    }

    @Test
    void testGetIncidentesPorEmpleado() throws Exception {
        int empleadoId = 123;

        Notificacion n = new Notificacion();
        n.setComentario("Incidente del empleado");

        when(notificacionService.getNotificacionesByEmpleado(empleadoId)).thenReturn(List.of(n));

        mockMvc.perform(get("/api/reportes/incidentes/empleado/{id}", empleadoId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].comentario").value("Incidente del empleado"));

        verify(notificacionService, times(1)).getNotificacionesByEmpleado(empleadoId);
    }

    @Test
    void testGetPruebasPorVehiculo() throws Exception {
        int vehiculoId = 456;

        Prueba p1 = new Prueba();


        Prueba p2 = new Prueba();

        when(pruebaService.findByVehiculoId(vehiculoId)).thenReturn(List.of(p1, p2));

        mockMvc.perform(get("/api/reportes/pruebas/vehiculo/{id}", vehiculoId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));

        verify(pruebaService, times(1)).findByVehiculoId(vehiculoId);
    }
    //GET /api/reportes/incidentes/empleado/{id} con ID inválido (negativo)
    @Test
    void testGetIncidentesPorEmpleadoConIdInvalido() throws Exception {
        int empleadoId = -1;

        mockMvc.perform(get("/api/reportes/incidentes/empleado/{id}", empleadoId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // porque el endpoint igual devuelve 200 con lista vacía
                .andExpect(jsonPath("$.size()").value(0));

        verify(notificacionService).getNotificacionesByEmpleado(empleadoId);
    }

    //GET /api/reportes/pruebas/vehiculo/{id} con lista vacía (vehículo sin pruebas)
    @Test
    void testGetPruebasPorVehiculoSinResultados() throws Exception {
        int vehiculoId = 999;

        when(pruebaService.findByVehiculoId(vehiculoId)).thenReturn(List.of());

        mockMvc.perform(get("/api/reportes/pruebas/vehiculo/{id}", vehiculoId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(0));

        verify(pruebaService).findByVehiculoId(vehiculoId);
    }

    @Test
    void testGetKilometrosPrueba_Exito() throws Exception {
        int id = 1;
        ReporteDTO dto = new ReporteDTO();
        dto.setFechaInicio(LocalDateTime.of(2025, 6, 1, 10, 0, 1)); // 2025-06-01T10:00
        System.out.println(dto.getFechaInicio());
        dto.setFechaFin(LocalDateTime.of(2025, 6, 2, 18, 30, 1));   // 2025-06-02T18:30
        //2025-06-01T00:00:00

        double kilometrosEsperados = 87.3;

        when(pruebaService.findByVehiculoIdAndFechaHoraInicioBetween(eq(id), any(), any()))
                .thenReturn(kilometrosEsperados);

        mockMvc.perform(get("/api/reportes/pruebas/km/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper()
                                .registerModule(new JavaTimeModule())
                                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                                .writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(kilometrosEsperados)));
    }


    @Test
    void testGetKilometrosPrueba_ErrorDeValidacion() throws Exception {
        int id = 1;
        ReporteDTO dtoInvalido = new ReporteDTO(); // campos nulos

        mockMvc.perform(get("/api/reportes/pruebas/km/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dtoInvalido)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$[0]").exists()); // Al menos un error
    }




}
