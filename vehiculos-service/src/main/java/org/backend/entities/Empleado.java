package org.backend.entities;
//legajo(int), nombre(string), apellido(string), telefono_contacto (int)
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity(name = "Empleados")
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)

public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LEGAJO")
    private int legajo;

    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "APELLIDO", nullable = false)
    private String apellido;

    @Column(name = "TELEFONO_CONTACTO", nullable = false)
    private Long telefono_contacto;

}
