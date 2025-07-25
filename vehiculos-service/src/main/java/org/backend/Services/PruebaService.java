package org.backend.Services;


import org.backend.Repository.PruebaRepository;
import org.backend.entities.Prueba;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PruebaService {

    @Autowired
    private final PruebaRepository pruebaRepository;

    public PruebaService(PruebaRepository pruebaRepository) {
        this.pruebaRepository = pruebaRepository;
    }

    public Prueba getPruebaActivasPorVehiculo(int vehiculoId) {
        return pruebaRepository.findByFechaHoraFinIsNullAndVehiculoId(vehiculoId);
    }
}
