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

    private Long id_vehiculo;
    private Long id_interesado;
    private  Long id_empleado;
}
