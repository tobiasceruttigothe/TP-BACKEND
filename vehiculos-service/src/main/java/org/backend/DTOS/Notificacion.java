package org.backend.DTOS;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.backend.entities.Empleado;
import org.backend.entities.Interesado;
import org.backend.entities.Vehiculo;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notificacion {

    private Empleado empleado;
    private Vehiculo vehiculo;
    private Interesado interesado;
    private String comentario;


}
