package org.backend.controller;


import org.backend.dtos.NotificacionesCreate;
import org.backend.entities.Notificacion;
import org.backend.entities.Promocion;
import org.backend.repository.NotificacionesRepository;
import org.backend.services.DsNotificacionService;
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
    @Autowired
    private DsNotificacionService dsNotificacionService;

    @GetMapping("/promociones")
    public ResponseEntity<List<Promocion>> promociones() {
        List<Promocion> promociones = promocionService.findAll();
        for (Promocion promo : promociones) {
            String mensaje = "¡🚨 ATENCION 🚨!\n" + promo.getDescripcion();
            dsNotificacionService.sendMessage(mensaje);
        }
        return ResponseEntity.ok().body(promociones);
    }

    /*@GetMapping("/promociones/discord")
    public ResponseEntity<String> enviarPromocionesADiscord() {
        List<Promocion> promociones = promocionService.findAll();
        for (Promocion promo : promociones) {
            String mensaje = "¡Promoción activa!\n" + promo.getDescripcion();
            dsNotificacionService.sendMessage(mensaje);
        }
        return ResponseEntity.ok("Promociones enviadas a Discord");
    }*/

    @PostMapping("/incidentes")
    public void incidentes(@RequestBody NotificacionesCreate notificacion) {
        notificacionesService.saveNotification(notificacion);
    }
}
