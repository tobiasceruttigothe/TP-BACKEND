package org.backend.DTOS;
import lombok.Data;
import java.util.List;

@Data
public class ConfiguracionAgencia {
    private Coordenada ubicacion_agencia;
    private int radio_maximo_metros;
    private List<ZonaPeligrosa> zonas_peligrosas;

    // getters y setters
}



