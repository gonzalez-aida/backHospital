package com.hospital.hospital.model.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class RecetaDTO {
    private Integer idReceta;
    private String folio;
    private LocalDate fecha;
    private LocalDate vencimiento;
    private String estado;

    private List<MedicamentoDTO> medicamentos;

    @Data
    public static class MedicamentoDTO {
        private String nombre;
        private String presentacion;
        private String dosis;
        private String frecuencia;
        private String duracion;
        private Short cantidad;
        private String via;
    }
}