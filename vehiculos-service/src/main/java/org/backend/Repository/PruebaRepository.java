package org.backend.Repository;

import org.backend.entities.Prueba;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PruebaRepository extends JpaRepository<Prueba, Long> {

    static Prueba findByVehiculoId(Long vehiculoId) {
        return null;
    }


    Prueba findByFechaHoraFinIsNullAndVehiculoId(Long vehiculoId);

}

