package org.backend.controller;

import jakarta.validation.Valid;
import org.backend.DTOS.Coordenada;
import org.backend.DTOS.PosicionDTOCreate;
import org.backend.Services.VehiculoService;
import org.backend.entities.Posicion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;
    @PostMapping
    public ResponseEntity<?> procesarNuevaPosicion(@Valid @RequestBody PosicionDTOCreate posicion, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errores = result.getFieldErrors()
                    .stream()
                    .map(e -> e.getField() + ": " + e.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errores);
        }
        vehiculoService.procesarNuevaPosicion(posicion);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/agencia/{id}")
    public void procesarNuevaPosicionAgencia(@PathVariable int id) {
        vehiculoService.procesarNuevaPosicionAgencia(id);
    }
}
