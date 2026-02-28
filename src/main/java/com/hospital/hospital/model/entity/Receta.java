package com.hospital.hospital.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "receta")
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_receta")
    private Integer idReceta;

    @Column(name = "folio", length = 30, nullable = false, unique = true)
    private String folio;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "vencimiento")
    private LocalDate vencimiento;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoReceta estado = EstadoReceta.activa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cita", nullable = false)
    private Cita cita;

    public enum EstadoReceta {
        activa, surtida, vencida, cancelada
    }
}