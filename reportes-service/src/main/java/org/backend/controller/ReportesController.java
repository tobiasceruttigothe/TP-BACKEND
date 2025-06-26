package org.backend.controller;


import org.backend.DTOS.ReporteDTO;
import org.backend.Repository.EmpleadoRepository;
import org.backend.Repository.NotificacionesRepository;
import org.backend.Repository.PruebaRepository;
import org.backend.entities.Empleado;
import org.backend.entities.Marca;
import org.backend.entities.Notificacion;
import org.backend.entities.Prueba;
import org.backend.services.NotificacionService;
import org.backend.services.PruebaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reportes")
public class ReportesController {

    //revisar si es necesario el repository de empleado y los @Autowired

    @Autowired
    private NotificacionService notificacionService;
    @Autowired
    private PruebaService pruebaService;

    @GetMapping("/incidentes")
    public ResponseEntity<List<Notificacion>> getAllIncidentes() {
        List<Notificacion> notificaciones = notificacionService.getNotificaciones();
        return ResponseEntity.ok(notificaciones);
    }

    @GetMapping("/incidentes/empleado/{id}")
    public ResponseEntity<List<Notificacion>> getIncidentesPorEmpleado(@PathVariable int id) {
        List<Notificacion> notificaciones = notificacionService.getNotificacionesByEmpleado(id);
        return ResponseEntity.ok(notificaciones);
    }

    @GetMapping("/pruebas/km/{id}")
    public ResponseEntity<Double> getKilometrosPrueba(@PathVariable int id, @RequestBody ReporteDTO reporteDTO) {
        double kmTotales = pruebaService.findByVehiculoIdAndFechaHoraInicioBetween(
                id,
                reporteDTO.getFechaInicio(),
                reporteDTO.getFechaFin()
        );
        return ResponseEntity.ok(kmTotales);
    }


    @GetMapping("/pruebas/vehiculo/{id}")
    public ResponseEntity<List<Prueba>> getPruebasPorVehiculo(@PathVariable int id) {
        List<Prueba> pruebas = pruebaService.findByVehiculoId(id);
        return ResponseEntity.ok(pruebas);
    }
}
