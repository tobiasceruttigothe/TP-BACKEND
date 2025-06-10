package org.backend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Vehiculos")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "PATENTE", nullable = false)
    private String patente;

    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MODELO", nullable = false)
    private Modelo modelo;


    @Column(name = "ANIO", nullable = false)
    private int anio;
}
