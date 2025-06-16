package org.backend.services;


import org.backend.dtos.NotificacionesCreate;
import org.backend.entities.Notificacion;
import org.backend.repository.NotificacionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class NotificacionesService {

    @Autowired
    private NotificacionesRepository notificacionesRepository;
    private final InteresadoService interesadoService;

    public NotificacionesService(InteresadoService interesadoService) {
        this.interesadoService = interesadoService;
    }

    public void saveNotification(NotificacionesCreate notificacioncreate) {
        Notificacion notificacion = new Notificacion();
        notificacion.setComentario(notificacioncreate.getComentario());
        notificacion.setInteresado(notificacioncreate.getInteresado());
        notificacion.setEmpleado(notificacioncreate.getEmpleado());
        notificacion.setVehiculo(notificacioncreate.getVehiculo());
        notificacion.setFecha(LocalDate.now());
        notificacionesRepository.save(notificacion);
        interesadoService.restringirInteresado(notificacion.getInteresado().getId());

    }
    //hay que dividir, un findAll para las promociones y otro para los incidentes
    public List<Notificacion> findAll() {
        return notificacionesRepository.findAll();
    }
}
