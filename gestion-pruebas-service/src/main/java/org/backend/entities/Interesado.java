package org.backend.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Column(name = "TIPO_DOCUMENTO", nullable = false)
    private String tipo_documento;

    @Column(name = "DOCUMENTO", nullable = false)
    private int documento;

    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "APELLIDO", nullable = false)
    private String apellido;

    @Column(name = "RESTRINGIDO", nullable = false)
    private int restringido;

    @Column(name = "NRO_LICENCIA", nullable = false)
    private int nroLicencia;

    @Column(name = "FECHA_VENCIMIENTO_LICENCIA", nullable = false)
    private String fechaVencimientoLicencia;

    //private Date fechaVencimiento;

    public LocalDate getFechaVencimiento() {
        //return java.sql.Date.valueOf(this.getFechaVencimientoLicencia());
        return LocalDate.parse(this.getFechaVencimientoLicencia()).atStartOfDay().toLocalDate();
    }
    //
}
