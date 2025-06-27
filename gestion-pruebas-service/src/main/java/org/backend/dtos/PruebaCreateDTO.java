package org.backend.dtos;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.backend.entities.Empleado;
import org.backend.entities.Interesado;
import org.backend.entities.Vehiculo;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PruebaCreateDTO {

    @NotNull(message = "debe ingresar un id vehiculo")
    private Long id_vehiculo;

    @NotNull(message = "debe ingresar un id interesado")
    private Long id_interesado;

    @NotNull(message = "debe ingresar un id empleado")
    private  Long id_empleado;
}
