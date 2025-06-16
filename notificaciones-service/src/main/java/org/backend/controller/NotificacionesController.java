package org.backend.controller;


import org.backend.dtos.NotificacionesCreate;
import org.backend.entities.Notificacion;
import org.backend.entities.Promocion;
import org.backend.repository.NotificacionesRepository;
import org.backend.services.NotificacionesService;
import org.backend.services.PromocionService;
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
    @Autowired
    private PromocionService promocionService;

    @GetMapping("/promociones")
    public ResponseEntity<List<Promocion>> promociones() {
        List<Promocion> promociones = promocionService.findAll();
        return ResponseEntity.ok().body(promociones);
    }

    @PostMapping("/incidentes")
    public void incidentes(@RequestBody NotificacionesCreate notificacion) {
        notificacionesService.saveNotification(notificacion);
    }
}
