package org.backend.Services;
import org.backend.DTOS.ConfiguracionAgencia;
import org.backend.DTOS.Coordenada;
import org.backend.DTOS.ZonaPeligrosa;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConfiguracionService {

    private static final String CONFIG_URL = "https://4e15fd8e-9dd1-4f3a-b058-2b3ef8a6f9bc.mock.pstmn.io/api/agency-config";

    private final RestTemplate restTemplate;
    private List<ZonaPeligrosa> zonasPeligrosas;
    private Coordenada ubicacionAgencia;
    private double radioMaximo;

    @Autowired
    public ConfiguracionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostConstruct
    public void cargarConfiguracion() {
        ResponseEntity<ConfiguracionAgencia> response = restTemplate.getForEntity(CONFIG_URL, ConfiguracionAgencia.class);

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            ConfiguracionAgencia config = response.getBody();
            this.zonasPeligrosas = config.getZonas_peligrosas();
            this.ubicacionAgencia = config.getUbicacion_agencia();
            this.radioMaximo = config.getRadio_maximo_metros();
            System.out.println("Configuración cargada correctamente.");
        } else {
            throw new IllegalStateException("No se pudo cargar la configuración inicial");
        }
    }

    public List<ZonaPeligrosa> getZonasPeligrosas() {
        return zonasPeligrosas;
    }

    public Coordenada getUbicacionAgencia() {
        return ubicacionAgencia;
    }

    public double getRadioMaximo() {
        return radioMaximo;
    }
}