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
    @Column(name = "ID")
    private int id;

    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MARCA", nullable = false)
    private Marca marca;


    @Column(name = "DESCRIPCION", nullable = false)
    private String descripcion;
}
