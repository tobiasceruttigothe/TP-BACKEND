package org.backend.services;


import org.backend.entities.Empleado;
import org.backend.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoService {

    private EmpleadoRepository empleadoRepository;
    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public Empleado getEmpleadoById(Long id) {
        return empleadoRepository.findById(id).orElse(null);
    }

}
