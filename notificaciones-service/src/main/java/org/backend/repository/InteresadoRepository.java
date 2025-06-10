package org.backend.repository;

import org.backend.entities.Interesado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InteresadoRepository extends  JpaRepository<Interesado, Long> {
    Optional<Interesado> findById(Long id);
}

