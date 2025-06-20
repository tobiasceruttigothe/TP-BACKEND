package org.backend.notificaciones.service.fake;

import org.backend.entities.Vehiculo;

public interface VehiculoRepository {
    Vehiculo getReferenceById(Long id);
}
