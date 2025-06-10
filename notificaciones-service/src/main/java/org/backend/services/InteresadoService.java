package org.backend.services;


import org.backend.entities.Interesado;
import org.backend.repository.InteresadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InteresadoService {

    @Autowired
    private InteresadoRepository interesadoRepository;

    public void restringirInteresado(Long id) {
        Interesado interesado = interesadoRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Interesado no encontrado"));
        interesado.setRestringido(1);
        interesadoRepository.save(interesado);
    }



}
