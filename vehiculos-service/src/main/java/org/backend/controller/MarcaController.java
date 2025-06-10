package org.backend.controller;


import org.backend.Repository.MarcaRepository;
import org.backend.entities.Marca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


//ESTO ES DE EJEMPLO PARA PROBAR LA BD, CREO QUE NO HACE FALTA, REVISAR
@RestController
@RequestMapping("/api/marcas")
public class MarcaController {

    @Autowired
    private MarcaRepository marcaRepository;

    @GetMapping
    public ResponseEntity<List<Marca>> getAllMarcas() {
        List<Marca> marcas = marcaRepository.findAll();
        return ResponseEntity.ok().body(marcas);
    }

}
