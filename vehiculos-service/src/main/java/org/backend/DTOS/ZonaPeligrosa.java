package org.backend.DTOS;
import lombok.Data;
import org.backend.DTOS.Coordenada;

@Data
public class ZonaPeligrosa {
    private String id_zona;
    private String nombre_zona;
    private Coordenada coordenadas;
    private double radio_metros;

    // lógica de `estaDentro()` se puede agregar acá si querés
}