package org.backend.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//id patente id_modelo
@Entity(name = "Vehiculos")
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
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
