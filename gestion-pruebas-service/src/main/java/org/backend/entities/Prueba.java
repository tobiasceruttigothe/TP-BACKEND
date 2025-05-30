package org.backend.entities;
//id(int), id_vehiculo(int), id_empleado(int), fecha_hora_inicio(timestamp), fecha_hora_fin(timestamp), comentarios(ztring)
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity(name = "Posiciones")
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Prueba {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // o cramos dto q haga referencia a vehiculo(y usamos manytoone y el join) o guardamos solo el id del vehiculo pq no deja import Vehiculo desde gestion-pruebas-service

    //@ManyToOne
    //@JoinColumn(name = "id_vehiculo", nullable = false)
    //private Vehiculo vehiculo;
    @Column(name = "id_vehiculo", nullable = false)
    private int id_vehiculo;

    @ManyToOne
    @JoinColumn(name = "id_empleado", nullable = false)
    private Empleado empleado;

    @Column(name = "fecha_hora_inicio", nullable = false)
    private LocalDateTime fecha_hora_inicio;

    @Column(name = "fecha_hora_fin", nullable = false)
    private LocalDateTime fecha_hora_fin;

    @Column(name = "comentarios", nullable = true)
    private String comentarios;

}
