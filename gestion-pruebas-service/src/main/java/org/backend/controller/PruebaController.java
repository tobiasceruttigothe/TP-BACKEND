package org.backend.controller;


import jakarta.validation.Valid;
import org.backend.dtos.PruebaCreateDTO;
import org.backend.entities.Prueba;
import org.backend.services.PruebaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
//import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;


@RestController
    @RequestMapping("/api/pruebas")
public class PruebaController {


    @Autowired
    PruebaService pruebaService;

    @GetMapping
    public ResponseEntity<List<Prueba>> getAllPruebas() {
        List<Prueba> pruebas = pruebaService.getAllPruebas();
        return ResponseEntity.ok(pruebas);
    }

    @GetMapping("/activas")
    public ResponseEntity<List<Prueba>> getPruebasActivas() {
        List<Prueba> pruebasActivas = pruebaService.getPruebasActivas();
        return ResponseEntity.ok(pruebasActivas);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> addPrueba(@Valid @RequestBody PruebaCreateDTO prueba, @RequestHeader("Authorization") String authHeader, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errores = result.getFieldErrors()
                    .stream()
                    .map(e -> e.getField() + ": " + e.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errores);
        }
        String token = authHeader.replace("Bearer ", "");
        Prueba savedPrueba = pruebaService.savePrueba(prueba, token);
        return ResponseEntity.ok(savedPrueba);
    }

    @PatchMapping("/finaliza/{id}")
    public ResponseEntity<?> finalizarPrueba(@PathVariable Long id, @Valid @RequestBody String comentario, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errores = result.getFieldErrors()
                    .stream()
                    .map(e -> e.getField() + ": " + e.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errores);
        }
        Prueba existingPrueba = pruebaService.getPruebaActivaById(id);
        if (existingPrueba == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pruebaService.finalizarPrueba(id, comentario));
    }

}
