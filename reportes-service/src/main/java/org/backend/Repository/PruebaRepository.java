package org.backend.Repository;



import org.backend.entities.Prueba;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PruebaRepository extends JpaRepository<Prueba, Long> {


    List<Prueba> findByVehiculoId(int id);
}
