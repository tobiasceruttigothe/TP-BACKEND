package org.backend.repository;

import org.backend.entities.Interesado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface InteresadoRepository extends JpaRepository<Interesado, Long> {

}
