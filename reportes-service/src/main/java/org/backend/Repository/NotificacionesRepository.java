package org.backend.Repository;


import org.backend.entities.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NotificacionesRepository extends JpaRepository<Notificacion, Long> {

    List<Notificacion> findByEmpleadoId();
}
