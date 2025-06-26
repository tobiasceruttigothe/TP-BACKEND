package org.backend.services;


import org.backend.Repository.PruebaRepository;
import org.backend.entities.Prueba;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PruebaService {

    @Autowired
    private PruebaRepository pruebaRepository;

    public PruebaService(PruebaRepository pruebaRepository) {
        this.pruebaRepository = pruebaRepository;
    }

    public List<Prueba> findByVehiculoId(int vehiculoId) {
        return pruebaRepository.findByVehiculoId(vehiculoId);
    }

    public double findByVehiculoIdAndFechaHoraInicioBetween(int vehiculoId, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin) {
        List<Prueba> pruebas = pruebaRepository
                .findByVehiculoIdAndFechaHoraInicioBetween(vehiculoId, fechaHoraInicio, fechaHoraFin);

        return pruebas.stream()
                .mapToDouble(Prueba::getKm)
                .sum();
    }
}
