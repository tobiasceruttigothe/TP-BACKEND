package org.backend.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Modelos")
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)

public class Modelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "id_marca", nullable = false)
    private int id_marca;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;
}
