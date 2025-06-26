package org.backend.DTOS;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReporteDTO {
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;


}
