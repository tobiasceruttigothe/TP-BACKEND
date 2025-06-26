package org.backend.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.backend.entities.Vehiculo;

@Data
public class PosicionDTOCreate {

    private Double longitud;
    private Double latitud;
    private int idVehiculo;

}
