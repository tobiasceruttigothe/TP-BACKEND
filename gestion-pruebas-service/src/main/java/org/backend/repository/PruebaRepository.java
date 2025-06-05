package org.backend.repository;

import org.backend.entities.Prueba;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PruebaRepository extends CrudRepository<Prueba, Long> {

    List<Prueba> findByFechaHoraFinIsNotNull();
}






