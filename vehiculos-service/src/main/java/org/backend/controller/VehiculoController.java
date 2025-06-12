package org.backend.controller;

import org.backend.DTOS.Coordenada;
import org.backend.Services.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;
    @PostMapping
    public void procesarNuevaPosicion(@RequestBody Coordenada coordenada) {
        vehiculoService.procesarNuevaPosicion(coordenada.getLatitud(), coordenada.getLongitud());
    }



/*
    @Autowired
    private MarcaRepository marcaRepository;

    @GetMapping
    public List<Marca> getAllMarcas() {
        List<Marca> marcas = marcaRepository.findAll();
        return marcas;
    }

 */

}
