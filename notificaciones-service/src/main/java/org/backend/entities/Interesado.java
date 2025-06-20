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
    private long id;
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

    @Column(name = "FECHA_VENCIMIENTO_LICENCIA", nullable = false)
    private String fechaVencimientoLicencia;

    public LocalDate getFechaVencimiento() {
        if (this.fechaVencimientoLicencia == null) {
            return null;
        }
        return LocalDate.parse(this.fechaVencimientoLicencia);
    }
    //lo modifique porque si fechaVencimientoLicencia era null, intentaba hacer LocalDate.parse(null),
    // lo cual tiraba error. ahora si fechaVencimientoLicencia es null, entonces retorna null directamente y evita el parse().

}
