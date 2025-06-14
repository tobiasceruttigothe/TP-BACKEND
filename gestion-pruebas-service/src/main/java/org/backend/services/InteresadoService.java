package org.backend.services;


import org.backend.entities.Interesado;
import org.backend.repository.InteresadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InteresadoService {
    @Autowired
    private InteresadoRepository interesadoRepository;


    public InteresadoService(InteresadoRepository interesadoRepository) {
        this.interesadoRepository = interesadoRepository;
    }
    public Interesado getInteresadoById(Long id) {
        return interesadoRepository.findById(id).orElse(null);
    }
}
