package org.backend.notificaciones.service.fake;


import org.backend.entities.Empleado;

public interface EmpleadoRepository {
    Empleado getReferenceById(Long id);
}
