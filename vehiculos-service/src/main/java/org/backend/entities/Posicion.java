package org.backend.entities;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Posiciones")
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)

public class Posicion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_VEHICULO", nullable = false)
    private Vehiculo vehiculo;


    @Column(name = "FECHA_HORA", nullable = false)
    private LocalDateTime fechaHora;

    @Column(name = "LATITUD", nullable = false)
    private double latitud;

    @Column(name = "LONGITUD", nullable = false)
    private double longitud;

}
