package org.backend.repository;


import org.backend.entities.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {

}
