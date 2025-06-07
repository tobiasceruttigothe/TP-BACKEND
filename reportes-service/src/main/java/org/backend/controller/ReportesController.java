package org.backend.controller;


import org.backend.Repository.EmpleadoRepository;
import org.backend.Repository.NotificacionesRepository;
import org.backend.entities.Empleado;
import org.backend.entities.Marca;
import org.backend.entities.Notificacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reportes")
public class ReportesController {

    @Autowired
    private NotificacionesRepository notificacionesRepository;

    @GetMapping("/incidentes")
    public List<Notificacion> getAllIncidentes() {
        List<Notificacion> notificaciones = notificacionesRepository.findAll();
        return notificaciones;
    }

    //@GetMapping("/incidentesPorEmpleado"){}

}
