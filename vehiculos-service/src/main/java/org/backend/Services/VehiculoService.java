package org.backend.Services;
import org.backend.DTOS.Coordenada;
import org.backend.DTOS.ZonaPeligrosa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehiculoService {
    @Autowired
    private ConfiguracionService configuracionService;

    public void procesarNuevaPosicion(double lat, double lon) {
        for (ZonaPeligrosa zona : configuracionService.getZonasPeligrosas()) {
            double distancia = calcularDistancia(lat, lon, zona.getCoordenadas());
            System.out.println("Zona: " + zona.getNombre_zona());
            System.out.println("Distancia al centro: " + distancia + " m");
            System.out.println("Radio de la zona " + zona.getNombre_zona() + ": " + zona.getRadio_metros() + " m");


            if (distancia <= zona.getRadio_metros()) {
                System.out.println("Vehículo dentro de la zona peligrosa: " + zona.getNombre_zona());
            } else {
                System.out.println("Vehículo fuera de la zona peligrosa: " + zona.getNombre_zona());
            }
        }
    }

    private double calcularDistancia(double latVehiculo, double lonVehiculo, Coordenada centroZona) {
        double deltaLat = latVehiculo - centroZona.getLatitud();
        double deltaLon = lonVehiculo - centroZona.getLongitud();

        // Usamos la latitud del centro para convertir longitud
        double deltaLatMetros = deltaLat * 111_320;
        double deltaLonMetros = deltaLon * 111_320 * Math.cos(Math.toRadians(centroZona.getLatitud()));

        return Math.sqrt(Math.pow(deltaLatMetros, 2) + Math.pow(deltaLonMetros, 2));
    }




}
