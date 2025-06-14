package org.backend.Services;
import org.backend.DTOS.ConfiguracionAgencia;
import org.backend.DTOS.Coordenada;
import org.backend.DTOS.ZonaPeligrosa;
import org.backend.entities.Agencia;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.backend.Services.AgenciaService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConfiguracionService {

    private static final String CONFIG_URL = "https://4e15fd8e-9dd1-4f3a-b058-2b3ef8a6f9bc.mock.pstmn.io/api/agency-config";
    private final AgenciaService agenciaService;
    private final RestTemplate restTemplate;
    private List<ZonaPeligrosa> zonasPeligrosas;


    @Autowired
    public ConfiguracionService(RestTemplate restTemplate, AgenciaService agenciaService) {
        this.restTemplate = restTemplate;
        this.agenciaService = agenciaService;
    }

    @PostConstruct
    public void cargarConfiguracion() {
        ResponseEntity<ConfiguracionAgencia> response = restTemplate.getForEntity(CONFIG_URL, ConfiguracionAgencia.class);

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            ConfiguracionAgencia config = response.getBody();
            Agencia agencia = new Agencia();
            agencia.setLongitud(config.getUbicacion_agencia().getLongitud());
            agencia.setLatitud(config.getUbicacion_agencia().getLatitud());
            agencia.setRadio(config.getRadio_maximo_metros());
            agenciaService.crearAgencia(agencia);

            this.zonasPeligrosas = config.getZonas_peligrosas();

            System.out.println("Configuración cargada correctamente.");
        } else {
            throw new IllegalStateException("No se pudo cargar la configuración inicial");
        }
    }

    public List<ZonaPeligrosa> getZonasPeligrosas() {
        return zonasPeligrosas;
    }
}