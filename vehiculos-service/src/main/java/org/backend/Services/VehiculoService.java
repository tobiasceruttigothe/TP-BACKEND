package org.backend.Services;
import org.backend.DTOS.ConfiguracionAgencia;
import org.backend.DTOS.Coordenada;
import org.backend.DTOS.Notificacion;
import org.backend.DTOS.ZonaPeligrosa;
import org.backend.entities.Prueba;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VehiculoService {
    @Autowired
    private ConfiguracionService configuracionService;
    @Autowired
    private PruebaService pruebaService;
    @Autowired
    private final RestTemplate restTemplate;


    public VehiculoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void procesarNuevaPosicion(double lat, double lon, Long idVehiculo) {
        Prueba prueba = pruebaService.getPruebaActivasPorVehiculo(idVehiculo);
        if (prueba == null) {
            System.out.println("No hay prueba activa para el vehículo con ID: " + idVehiculo);
            return;
        }
        /*
        Notificacion notificacion = new Notificacion();
        notificacion.setVehiculo(prueba.getVehiculo());
        notificacion.setEmpleado(prueba.getEmpleado());
        notificacion.setInteresado(prueba.getInteresado());


         */



        for (ZonaPeligrosa zona : configuracionService.getZonasPeligrosas()) {
            System.out.println("Procesando zona: " + zona.getNombre_zona());
            double distancia = calcularDistancia(lat, lon, zona.getCoordenadas());
            if (distancia <= zona.getRadio_metros()) {
                System.out.println("Vehículo dentro de la zona peligrosa: " + zona.getNombre_zona());
               /*
                       try {
            restTemplate.postForObject(
                    "http://localhost:8080/api/notificaciones/incidentes",
                    notificacion,
                    Void.class
            );
            System.out.println("Notificación enviada para zona: " + zona.getNombre_zona());
        } catch (Exception e) {
            System.err.println("Error enviando notificación: " + e.getMessage());
        }
                */


            } else {
                System.out.println("Vehículo fuera de la zona peligrosa: " + zona.getNombre_zona());
            }
        }
    }

    private double calcularDistancia(double latVehiculo, double lonVehiculo, Coordenada centroZona) {

        double deltaLat = latVehiculo - centroZona.getLatitud();
        double deltaLon = lonVehiculo - centroZona.getLongitud();


        double deltaLatMetros = deltaLat * 111_319.9;
        double deltaLonMetros = deltaLon * 111_319.9 * Math.cos(Math.toRadians(centroZona.getLatitud()));


        double distancia = Math.sqrt(deltaLatMetros * deltaLatMetros + deltaLonMetros * deltaLonMetros);

        return distancia;
    }



}
