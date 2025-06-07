package org.backend.Repository;


import org.backend.entities.Empleado;
import org.backend.entities.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

}
