package org.backend.DTOS;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coordenada {
    private double latitud;
    private double longitud;

    // getters y setters
}