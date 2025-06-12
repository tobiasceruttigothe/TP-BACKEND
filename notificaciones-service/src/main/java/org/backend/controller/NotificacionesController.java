package org.backend.controller;


import org.backend.dtos.NotificacionesCreate;
import org.backend.entities.Notificacion;
import org.backend.repository.NotificacionesRepository;
import org.backend.services.NotificacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionesController {

    @Autowired
    private NotificacionesService notificacionesService;

    @GetMapping("/promociones")
    public ResponseEntity<List<Notificacion>> promociones() {
        //REVISAR ESTE ENDPOINT NO TODAS SON PROMOCIONES
        List<Notificacion> notificaciones = notificacionesService.findAll();
        return ResponseEntity.ok().body(notificaciones);
    }

    @PostMapping("/incidentes")
    public void incidentes(@RequestBody NotificacionesCreate notificacion) {
        notificacionesService.saveNotification(notificacion);
    }
}
