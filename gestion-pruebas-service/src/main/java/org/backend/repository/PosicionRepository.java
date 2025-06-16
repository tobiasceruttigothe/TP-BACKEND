package org.backend.repository;

import org.backend.entities.Interesado;
import org.backend.entities.Posicion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PosicionRepository extends JpaRepository<Posicion, Long> {

    List<Posicion> findByVehiculoIdAndFechaHoraBetweenOrderByFechaHoraAsc(int vehiculoId, LocalDateTime desde, LocalDateTime hasta);

}
