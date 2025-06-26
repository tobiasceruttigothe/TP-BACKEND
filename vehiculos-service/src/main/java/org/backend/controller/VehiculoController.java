package org.backend.controller;

import org.backend.DTOS.Coordenada;
import org.backend.DTOS.PosicionDTOCreate;
import org.backend.Services.VehiculoService;
import org.backend.entities.Posicion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;
    @PostMapping
    public void procesarNuevaPosicion(@RequestBody PosicionDTOCreate posicion) {
        vehiculoService.procesarNuevaPosicion(posicion);
    }

    @GetMapping("/agencia/{id}")
    public void procesarNuevaPosicionAgencia(@PathVariable int id) {
        vehiculoService.procesarNuevaPosicionAgencia(id);
    }
}
