package org.backend.entities;
// id, tipo_documento, documento, nombre, apellido, restringido, nro_licencia, fecha_venicimiento_licencia

import jakarta.persistence.*;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Interesados")
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)

public class Interesado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "tipo_documento", nullable = false)
    private String tipo_documento;

    @Column(name = "documento", nullable = false)
    private String documento;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellido", nullable = false)
    private String apellido;

    @Column(name = "restringido", nullable = false)
    private boolean restringido;

    @Column(name = "nro_licencia", nullable = false)
    private int nroLicencia;

    @Column(name = "fecha_vencimiento_licencia", nullable = false)
    private LocalDateTime fechaVenicimientoLicencia;
}
