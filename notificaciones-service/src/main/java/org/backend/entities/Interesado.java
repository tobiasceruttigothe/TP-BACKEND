package org.backend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Table(name = "Interesados")
public class Interesado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "TIPO_DOCUMENTO")
    private String tipoDoc;
    @Column(name = "DOCUMENTO")
    private String documento;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "APELLIDO")
    private String apellido;
    @Column(name = "RESTRINGIDO")
    private int restringido;
    @Column(name = "NRO_LICENCIA")
    private int nroLicencia;
    @Column(name = "FECHA_VENCIMIENTO_LICENCIA")
    private LocalDate fechaVencimientoLicencia;
}
