package org.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Pruebas")
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Prueba {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int ID;

    @Column(name = "FECHA_HORA_INICIO", nullable = false)
    private LocalDateTime fechaHoraInicio;

    @Column(name = "FECHA_HORA_FIN", nullable = false)
    private LocalDateTime fechaHoraFin;

    @Column(name = "COMENTARIOS", nullable = true)
    private String comentarios;

    @ManyToOne
    @JoinColumn(name = "ID_VEHICULO", nullable = false)
    private Vehiculo vehiculo;

    @Column(name = "KM")
    private Double km;

    @ManyToOne
    @JoinColumn(name = "ID_INTERESADO", nullable = false)
    private Interesado interesado;

    @ManyToOne
    @JoinColumn(name = "ID_EMPLEADO", nullable = false)
    private Empleado empleado;

}
