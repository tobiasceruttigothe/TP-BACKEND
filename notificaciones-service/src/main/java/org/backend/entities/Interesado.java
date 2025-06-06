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
    @Column(name = "id")
    private int id;
    @Column(name = "tipo_documento")
    private String tipoDoc;
    @Column(name = "documento")
    private String documento;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "restringido")
    private int restringido;
    @Column(name = "numero_licencia")
    private int nroLicencia;
    @Column(name = "fecha_vencimiento_licencia")
    private LocalDate fechaVencimientoLicencia;
}
