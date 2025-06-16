package org.backend.Services;
import org.backend.DTOS.*;
import org.backend.entities.*;
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
    @Autowired
    private PosicionService posicionService;
    @Autowired
    private AgenciaService agenciaService;


    public VehiculoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void procesarNuevaPosicion(PosicionDTOCreate posicionDTO) {

        Prueba prueba = pruebaService.getPruebaActivasPorVehiculo(posicionDTO.getIdVehiculo());
        if (prueba == null) {
            System.out.println("No hay prueba activa para el vehículo con ID: " + posicionDTO.getIdVehiculo());
            return;
        }
        nuevaPosicion(posicionDTO);
        Agencia agencia = agenciaService.obtenerAgencia();
        // distanciaAgencia es la distancia entre el vehículo y la agencia
        double distanciaAgencia = calcularDistancia(
                posicionDTO.getLatitud(),
                posicionDTO.getLongitud(),
                new Coordenada(agencia.getLatitud(), agencia.getLongitud()));

        for (ZonaPeligrosa zona : configuracionService.getZonasPeligrosas()) {

            // distancia es la distancia entre el vehículo y el centro de la zona peligrosa
            double distancia = calcularDistancia(
                    posicionDTO.getLatitud(),
                    posicionDTO.getLongitud(),
                    zona.getCoordenadas());


            if (distancia <= zona.getRadio_metros()) {
                System.out.println("Vehículo dentro de la zona peligrosa: " + zona.getNombre_zona());

                String comentario = "Vehículo detectado en zona peligrosa: " + zona.getNombre_zona();

                enviarNotificacion(prueba.getVehiculo(), prueba.getEmpleado(), prueba.getInteresado(), comentario);

                //esto simula el regreso a la agencia,
                // solo se puede finalizar una prueba si el vehículo está dentro de la agencia
                PosicionDTOCreate posicionAgencia = new PosicionDTOCreate();
                posicionAgencia.setLatitud(agencia.getLatitud());
                posicionAgencia.setLongitud(agencia.getLongitud());
                posicionAgencia.setIdVehiculo(posicionDTO.getIdVehiculo());

                nuevaPosicion(posicionAgencia);
                break;
            }
        }
        if (distanciaAgencia > agencia.getRadio()) {

            String comentario = "Vehículo fuera del radio de la agencia.";
            enviarNotificacion(prueba.getVehiculo(), prueba.getEmpleado(), prueba.getInteresado(), comentario);

            //esto simula el regreso a la agencia,
            // solo se puede finalizar una prueba si el vehículo está dentro de la agencia
            PosicionDTOCreate posicionAgencia = new PosicionDTOCreate();
            posicionAgencia.setLatitud(agencia.getLatitud());
            posicionAgencia.setLongitud(agencia.getLongitud());
            posicionAgencia.setIdVehiculo(posicionDTO.getIdVehiculo());
            nuevaPosicion(posicionAgencia);
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

    public void nuevaPosicion(PosicionDTOCreate posicionDTOCreate) {
        posicionService.crearPosicion(posicionDTOCreate);
    }

    public void enviarNotificacion(Vehiculo vehiculo, Empleado empleado, Interesado interesado, String comentario) {

        Notificacion notificacion = new Notificacion(empleado, vehiculo, interesado, comentario);
        try {
            restTemplate.postForObject(
                "http://localhost:8080/api/notificaciones/incidentes",
                notificacion, Void.class
            );
            System.out.println("Notificación enviada: " + notificacion.getComentario());
        } catch (Exception e) {
            System.err.println("Error enviando notificación: " + e.getMessage());
        }
    }
}
