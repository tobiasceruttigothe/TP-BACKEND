package org.backend.DTOS;

import lombok.Data;
import org.backend.entities.Vehiculo;

@Data
public class PosicionDTOCreate {

    private Double longitud;
    private Double latitud;
    private Long idVehiculo;


}
