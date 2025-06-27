package org.backend.dtos;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.backend.entities.Empleado;
import org.backend.entities.Interesado;
import org.backend.entities.Vehiculo;

@Data
@AllArgsConstructor
public class NotificacionesCreate {



    @NotNull(message = "debe ingresar un empleado")
    private Empleado empleado;

    @NotNull(message = "debe ingresar un vehiculo")
    private Vehiculo vehiculo;

    @NotNull(message = "debe ingresar un intersado")
    private Interesado interesado;

    @NotNull(message = "debe ingresar un comentario")
    private String comentario;


}
