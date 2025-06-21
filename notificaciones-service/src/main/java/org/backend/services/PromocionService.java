package org.backend.services;


import org.backend.entities.Promocion;
import org.backend.repository.PromocionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromocionService {

    private PromocionRepository promocionRepository;
    @Autowired
    private DsNotificacionService dsNotificacionService;

    public PromocionService(PromocionRepository promocionRepository) {
        this.promocionRepository = promocionRepository;
    }

    public List<Promocion> findAll(){
        return promocionRepository.findAll();
    }

}
