package org.backend.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.backend.entities.Empleado;
import org.backend.entities.Interesado;
import org.backend.entities.Vehiculo;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PruebaCreateDTO {

    private LocalDateTime fechaHoraInicio;
    private Vehiculo vehiculo;
    private Interesado interesado;
    private Empleado empleado;

}
