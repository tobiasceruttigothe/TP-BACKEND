package org.backend.services;


import org.backend.dtos.PruebaCreateDTO;
import org.backend.entities.Prueba;
import org.backend.repository.PruebaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PruebaService {

    private final PruebaRepository pruebaRepository;

    public PruebaService(PruebaRepository pruebaRepository) {
        this.pruebaRepository = pruebaRepository;
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

    public Prueba savePrueba(PruebaCreateDTO pruebaAControlar) {
        PruebaCreateDTO prueba = controlesPrueba(pruebaAControlar);
        Prueba nuevaPrueba = new Prueba();
        nuevaPrueba.setFechaHoraInicio(prueba.getFechaHoraInicio());
        nuevaPrueba.setVehiculo(prueba.getVehiculo());
        nuevaPrueba.setInteresado(prueba.getInteresado());
        nuevaPrueba.setEmpleado(prueba.getEmpleado());
        return pruebaRepository.save(nuevaPrueba);
    }


    public PruebaCreateDTO controlesPrueba(PruebaCreateDTO prueba) {

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
            if (prueba.getInteresado().getFechaVenicimientoLicencia().isBefore(LocalDate.now())) {
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
}
