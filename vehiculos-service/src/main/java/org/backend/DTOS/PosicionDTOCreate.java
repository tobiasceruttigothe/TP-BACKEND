package org.backend.DTOS;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.backend.entities.Vehiculo;

@Data
public class PosicionDTOCreate {



    @NotNull(message = "debe ingresar una longitud")
    private Double longitud;

    @NotNull(message = "debe ingresar una latitud")
    private Double latitud;

    @NotNull(message = "debe ingresar un id vehiculo")
    @Min(1)
    private int idVehiculo;

}
