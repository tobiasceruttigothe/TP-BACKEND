package org.backend.Repository;


import org.backend.entities.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface NotificacionesRepository extends JpaRepository<Notificacion, Long> {

}
