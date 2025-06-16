package org.backend.Repository;

import org.backend.entities.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {

}
