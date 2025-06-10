package org.backend.controller;


import org.backend.Repository.EmpleadoRepository;
import org.backend.Repository.NotificacionesRepository;
import org.backend.Repository.PruebaRepository;
import org.backend.entities.Empleado;
import org.backend.entities.Marca;
import org.backend.entities.Notificacion;
import org.backend.entities.Prueba;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
public class ReportesController {

    //revisar si es necesario el repository de empleado y los @Autowired

    @Autowired
    private NotificacionesRepository notificacionesRepository;
    @Autowired
    private PruebaRepository pruebaRepository;

    @GetMapping("/incidentes")
    public ResponseEntity<List<Notificacion>> getAllIncidentes() {
        List<Notificacion> notificaciones = notificacionesRepository.findAll();
        return ResponseEntity.ok(notificaciones);
    }

    @GetMapping("/incidentes/empleado/{id}")
    public ResponseEntity<Notificacion> getIncidentesPorEmpleado() {
        Notificacion notificacion = notificacionesRepository.findByEmpleadoId();
        return ResponseEntity.ok(notificacion);
    }

    /*@GetMapping("/pruebas/km/{id}")
    public Double getKilometrosPrueba() {

    }

     */
    @GetMapping("/pruebas/vehiculo/{id}")
    public ResponseEntity<List<Prueba>> getPruebasPorVehiculo(@PathVariable Long id) {
        List<Prueba> pruebas = pruebaRepository.findByVehiculoId(id);
        return ResponseEntity.ok(pruebas);
    }
}
