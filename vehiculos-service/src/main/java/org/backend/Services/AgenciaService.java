package org.backend.Services;


import org.backend.Repository.AgenciaRepository;
import org.backend.entities.Agencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgenciaService {


    @Autowired
    private AgenciaRepository agenciaRepository;

    public void crearAgencia(Agencia agencia) {
        agenciaRepository.save(agencia);
    }

}
