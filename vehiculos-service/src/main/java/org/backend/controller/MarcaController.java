package org.backend.controller;


import org.backend.Repository.MarcaRepository;
import org.backend.entities.Marca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/marcas")
public class MarcaController {

    @Autowired
    private MarcaRepository marcaRepository;

    @GetMapping
    public List<Marca> getAllMarcas() {
        List<Marca> marcas = marcaRepository.findAll();
        return marcas;
    }

}
