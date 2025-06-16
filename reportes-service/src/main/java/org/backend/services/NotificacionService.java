package org.backend.services;


import org.backend.Repository.NotificacionesRepository;
import org.backend.entities.Empleado;
import org.backend.entities.Notificacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificacionService {


    @Autowired
    private NotificacionesRepository notificacionRepository;

    public NotificacionService(NotificacionesRepository notificacionRepository) {
        this.notificacionRepository = notificacionRepository;
    }

    public List<Notificacion> getNotificaciones() {
        return notificacionRepository.findAll();
    }

    public List<Notificacion> getNotificacionesByEmpleado(int id) {
        return notificacionRepository.findByEmpleado_Legajo(id);

    }

}
