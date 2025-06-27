package org.backend.services;


import org.backend.dtos.PruebaCreateDTO;
import org.backend.entities.*;
import org.backend.repository.PosicionRepository;
import org.backend.repository.PruebaRepository;
import org.backend.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.ReactiveSecurityContextHolder;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PruebaService {

    //private final WebClient webClient;


    private final PruebaRepository pruebaRepository;
    @Autowired
    private final VehiculoService vehiculoService;

    @Autowired
    private final InteresadoService interesadoService;

    @Autowired
    private final EmpleadoService empleadoService;

    @Autowired
    private PosicionRepository posicionRepository;

    @Autowired
    private final RestTemplate restTemplate;

    public PruebaService(PruebaRepository pruebaRepository, VehiculoService vehiculoService, InteresadoService interesadoService, EmpleadoService empleadoService, RestTemplate restTemplate) {
        //this.webClient = webClient;
        this.pruebaRepository = pruebaRepository;
        this.vehiculoService = vehiculoService;
        this.interesadoService = interesadoService;
        this.empleadoService = empleadoService;
        this.restTemplate = restTemplate;
    }


    public List<Prueba> getAllPruebas() {
        return (List<Prueba>) pruebaRepository.findAll();
    }

    public List<Prueba> getPruebasActivas() {

        return pruebaRepository.findByFechaHoraFinIsNull();
    }

    public Prueba getPruebaActivaById(Long id) {
        List<Prueba> pruebas = getPruebasActivas();
        for (Prueba prueba : pruebas) {
            if (prueba.getId() == (id)) {
                return prueba;
            }
        }
        return null;
    }


    public Prueba savePrueba(PruebaCreateDTO pruebaCreateDTO, String token) {
        Prueba pruebaAControlar = findAndMatch(pruebaCreateDTO);
        Prueba nuevaPrueba = controlesPrueba(pruebaAControlar);
        int id = nuevaPrueba.getVehiculo().getId();

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(token);// asegúrate de que el token esté actualizado y válido

            HttpEntity<Void> entity = new HttpEntity<>(headers);

            restTemplate.exchange(
                    "http://localhost:8080/api/vehiculos/agencia/" + id,
                    HttpMethod.GET,
                    entity,
                    Void.class
            );
            System.out.println("seteando como primer posición la agencia");
        } catch (Exception e) {
            System.err.println("Error seteando agencia: " + e.getMessage());
        }
        return pruebaRepository.save(nuevaPrueba);

    }


    public Prueba controlesPrueba(Prueba prueba) {

        List<Prueba> pruebasActivas = getPruebasActivas();

        for (Prueba p : pruebasActivas) {

            if (p.getVehiculo().getId() == (prueba.getVehiculo().getId())) {
                throw new IllegalArgumentException("El vehículo ya tiene una prueba activa");
            }
            if (p.getInteresado().getId() == (prueba.getInteresado().getId())) {
                throw new IllegalArgumentException("El interesado ya tiene una prueba activa");
            }
            if (p.getEmpleado().getLegajo() == (prueba.getEmpleado().getLegajo())) {
                throw new IllegalArgumentException("El empleado ya tiene una prueba activa");
            }
        }

            if (prueba.getInteresado().getFechaVencimiento().isBefore(LocalDate.now())) {
                throw new IllegalArgumentException("El interesado no tiene licencia vigente");
            }
            if (prueba.getInteresado().getRestringido() == 1) {
                throw new IllegalArgumentException("El interesado tiene restricciones en su licencia");
            }

        return prueba;
    }

    public Prueba finalizarPrueba(Long id, String comentario) {
        Prueba prueba = getPruebaActivaById(id);
        if (prueba != null) {
            double kilometros = calcularKmRecorridos(prueba);

            prueba.setFechaHoraFin(LocalDateTime.now());
            prueba.setComentarios(comentario);
            prueba.setKm(kilometros);
            return pruebaRepository.save(prueba);
        }
        return pruebaRepository.findById(id).orElse(null);
    }

    public Prueba findAndMatch(PruebaCreateDTO pruebaCreateDTO) {
        Prueba prueba = new Prueba();
        Vehiculo vehiculo = new Vehiculo();
        vehiculo = vehiculoService.getVehiculoById(pruebaCreateDTO.getId_vehiculo());
        Interesado interesado = new Interesado();
        interesado = interesadoService.getInteresadoById(pruebaCreateDTO.getId_interesado());
        System.out.println(interesado.getFechaVencimiento());
        Empleado empleado = new Empleado();
        empleado = empleadoService.getEmpleadoById(pruebaCreateDTO.getId_empleado());

        prueba.setVehiculo(vehiculo);
        prueba.setInteresado(interesado);
        prueba.setEmpleado(empleado);
        prueba.setFechaHoraInicio(LocalDateTime.now());
        //prueba.setFechaHoraFin(null);
        //prueba.setComentarios(null);
        return prueba;
    }

    public double calcularKmRecorridos(Prueba prueba) {
        int idVehiculo = prueba.getVehiculo().getId();
        LocalDateTime inicio = prueba.getFechaHoraInicio();
        LocalDateTime fin = LocalDateTime.now();

        List<Posicion> posiciones = posicionRepository
                .findByVehiculoIdAndFechaHoraBetweenOrderByFechaHoraAsc(idVehiculo, inicio, fin);

        double totalMetros = 0.0;

        for (int i = 1; i < posiciones.size(); i++) {
            Posicion p1 = posiciones.get(i - 1);
            Posicion p2 = posiciones.get(i);
            totalMetros += calcularDistancia(
                    p1.getLatitud(), p1.getLongitud(),
                    p2.getLatitud(), p2.getLongitud()
            );
        }

        return totalMetros / 1000.0;
    }

    private double calcularDistancia(double lat1, double lon1, double lat2, double lon2) {
        double deltaLat = lat1 - lat2;
        double deltaLon = lon1 - lon2;

        double deltaLatMetros = deltaLat * 111_319.9;
        double deltaLonMetros = deltaLon * 111_319.9 * Math.cos(Math.toRadians(lat2));

        return Math.sqrt(deltaLatMetros * deltaLatMetros + deltaLonMetros * deltaLonMetros);
    }



}
