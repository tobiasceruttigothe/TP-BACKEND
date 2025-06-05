package org.backend.controller;


import org.backend.dtos.PruebaCreateDTO;
import org.backend.entities.Prueba;
import org.backend.services.PruebaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/pruebas")
public class PruebaController {


    @Autowired
    PruebaService pruebaService;

    @GetMapping
    public ResponseEntity<List<Prueba>> getAllPruebas() {
        List<Prueba> pruebas = pruebaService.getAllPruebas();
        return ResponseEntity.ok(pruebas);
    }

    @PostMapping("/add")
    public ResponseEntity<Prueba> addPrueba(PruebaCreateDTO prueba) {
        Prueba savedPrueba = pruebaService.savePrueba(prueba);
        return ResponseEntity.ok(savedPrueba);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Prueba> finalizarPrueba(@PathVariable Long id, @RequestBody String comentario) {
        Prueba existingPrueba = pruebaService.getPruebaById(id);
        if (existingPrueba == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pruebaService.finalizarPrueba(id, comentario));
    }


}
