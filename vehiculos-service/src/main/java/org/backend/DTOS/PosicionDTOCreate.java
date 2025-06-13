package org.backend.DTOS;

import lombok.Data;
import org.backend.entities.Vehiculo;

@Data
public class PosicionDTOCreate {

    private Coordenada coordenada;
    private Long idVehiculo;


}
