package org.backend.DTOS;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReporteDTO {



    @NotNull(message = "debe ingresar una fecha hora de inicio")
    //@Past(message = "La fecha debe estar en el pasado")
    private LocalDateTime fechaInicio;

    @NotNull(message = "debe ingresar una fecha hora fin")
    private LocalDateTime fechaFin;

}
