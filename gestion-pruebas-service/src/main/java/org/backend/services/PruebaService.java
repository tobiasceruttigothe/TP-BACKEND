package org.backend.services;


import org.backend.dtos.PruebaCreateDTO;
import org.backend.entities.Empleado;
import org.backend.entities.Interesado;
import org.backend.entities.Prueba;
import org.backend.entities.Vehiculo;
import org.backend.repository.PruebaRepository;
import org.backend.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PruebaService {

    private final PruebaRepository pruebaRepository;
    @Autowired
    private final VehiculoService vehiculoService;

    @Autowired
    private final InteresadoService interesadoService;

    @Autowired
    private final EmpleadoService empleadoService;

    public PruebaService(PruebaRepository pruebaRepository, VehiculoService vehiculoService, InteresadoService interesadoService, EmpleadoService empleadoService) {
        this.pruebaRepository = pruebaRepository;
        this.vehiculoService = vehiculoService;
        this.interesadoService = interesadoService;
        this.empleadoService = empleadoService;
    }


    public List<Prueba> getAllPruebas() {
        return (List<Prueba>) pruebaRepository.findAll();
    }

    public List<Prueba> getPruebasActivas() {

        return pruebaRepository.findByFechaHoraFinIsNotNull();
    }

    public Prueba getPruebaById(Long id) {
        return pruebaRepository.findById(id).orElse(null);
    }

    public Prueba savePrueba(PruebaCreateDTO pruebaCreateDTO) {
        Prueba pruebaAControlar = findAndMatch(pruebaCreateDTO);
        Prueba nuevaPrueba = controlesPrueba(pruebaAControlar);

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
            if (prueba.getInteresado().getFechaVencimiento().isBefore(LocalDate.now())) {
                throw new IllegalArgumentException("El interesado no tiene licencia vigente");
            }
            if (prueba.getInteresado().getRestringido() != 0) {
                throw new IllegalArgumentException("El interesado tiene restricciones en su licencia");
            }
        }
        return prueba;
    }

    public Prueba finalizarPrueba(Long id, String comentario) {
        Prueba prueba = getPruebaById(id);
        if (prueba != null) {
            prueba.setFechaHoraFin(LocalDateTime.now());
            prueba.setComentarios(comentario);
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
}
