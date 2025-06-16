package org.backend.Services;


import org.backend.DTOS.PosicionDTOCreate;
import org.backend.Repository.PosicionRepository;
import org.backend.Repository.VehiculoRepository;
import org.backend.entities.Posicion;
import org.backend.entities.Vehiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PosicionService {

    @Autowired
    private final PosicionRepository posicionRepository;
    private  final VehiculoRepository vehiculoRepository;

    public PosicionService(PosicionRepository posicionRepository, VehiculoRepository vehiculoRepository) {
        this.posicionRepository = posicionRepository;
        this.vehiculoRepository = vehiculoRepository;
    }

    public void crearPosicion(PosicionDTOCreate posicion) {
        Posicion nuevaPosicion = new Posicion();
        nuevaPosicion.setLatitud(posicion.getLatitud());
        nuevaPosicion.setLongitud(posicion.getLongitud());
        nuevaPosicion.setFechaHora(LocalDateTime.now());
        Vehiculo vehiculo = new Vehiculo();
        vehiculo = vehiculoRepository.findById(posicion.getIdVehiculo()).orElse(null);
        nuevaPosicion.setVehiculo(vehiculo);
        posicionRepository.save(nuevaPosicion);
    }

}
