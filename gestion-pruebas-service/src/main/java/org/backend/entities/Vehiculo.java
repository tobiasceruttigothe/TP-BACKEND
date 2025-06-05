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


    @Column(name = "PATENTE")
    private String patente;

}
