package org.backend.entities;
//id(int), id_vehiculo(int), id_empleado(int), fecha_hora_inicio(timestamp), fecha_hora_fin(timestamp), comentarios(ztring)
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
    @Column(name = "ID", nullable = false)
    private int ID;

    @Column(name = "FECHA_HORA_INICIO", nullable = false)
    private LocalDateTime fechaHoraInicio;

    @Column(name = "FECHA_HORA_FIN", nullable = false)
    private LocalDateTime fechaHoraFin;

    @Column(name = "COMENTARIOS", nullable = true)
    private String comentarios;

    @Column(name = "KM")
    private Double km;

    @ManyToOne
    @JoinColumn(name = "ID_VEHICULO", nullable = false)
    private Vehiculo vehiculo;

    @ManyToOne
    @JoinColumn(name = "ID_INTERESADO", nullable = false)
    private Interesado interesado;

    @ManyToOne
    @JoinColumn(name = "ID_EMPLEADO", nullable = false)
    private Empleado empleado;
    // o cramos dto q haga referencia a vehiculo(y usamos manytoone y el join) o guardamos solo el id del vehiculo pq no deja import Vehiculo desde gestion-pruebas-service
}
