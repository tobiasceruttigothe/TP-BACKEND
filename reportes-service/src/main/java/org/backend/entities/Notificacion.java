package org.backend.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "notificaciones")
@Data
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne //que es FetchType.LAZY? (fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_EMPLEADO", nullable = false)
    private Empleado empleado;

    @Column(name = "FECHA_INCIDENTE", nullable = false)
    private LocalDate fecha;

    @Column(name = "COMENTARIO", nullable = false)
    private String comentario;

    @OneToOne
    @JoinColumn(name = "ID_INTERESADO", nullable = false)
    private Interesado interesado;

    @ManyToOne
    @JoinColumn(name = "ID_VEHICULO", nullable = false)
    private Vehiculo vehiculo;

    public Notificacion(Empleado empleado, String comentario, Interesado interesado, Vehiculo vehiculo) {
        this.empleado = empleado;
        this.fecha = LocalDate.now();
        this.comentario = comentario;
        this.interesado = interesado;
        this.vehiculo = vehiculo;
    }

    public Notificacion() {

    }
}




