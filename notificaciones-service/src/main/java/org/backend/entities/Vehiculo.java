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
    private int id;

    @Column(name = "patente", nullable = false)
    private String patente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_modelo", nullable = false)
    private Modelo modelo;


    @Column(name = "anio", nullable = false)
    private int anio;

}
