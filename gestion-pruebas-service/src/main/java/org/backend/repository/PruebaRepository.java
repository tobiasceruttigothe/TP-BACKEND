package org.backend.repository;

import org.backend.entities.Prueba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PruebaRepository extends JpaRepository<Prueba, Long> {

    List<Prueba> findByFechaHoraFinIsNull();
}






