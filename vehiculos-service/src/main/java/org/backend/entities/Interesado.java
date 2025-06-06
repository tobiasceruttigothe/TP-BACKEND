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
    @Column(name = "ID")
    private int id;

    @Column(name = "TIPO_DOCUMENTO", nullable = false)
    private String tipo_documento;

    @Column(name = "DOCUMENTO", nullable = false)
    private String documento;

    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "APELLIDO", nullable = false)
    private String apellido;

    @Column(name = "RESTRINGIDO", nullable = false)
    private boolean restringido;

    @Column(name = "NRO_LICENCIA", nullable = false)
    private int nroLicencia;

    @Column(name = "FECHA_VENCIMIENTO_LICENCIA", nullable = false)
    private LocalDateTime fechaVenicimientoLicencia;
}
