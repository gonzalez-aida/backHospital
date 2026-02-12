package com.hospital.hospital.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "cita")
public class Cita {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cita")
    private Integer idCita;
    
    @Column(name = "fecha")
    private LocalDate fecha;
    
    @Column(name = "hora")
    private LocalTime hora;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 20)
    private EstadoCita estado = EstadoCita.PROGRAMADA;
    
    @Column(name = "motivo", length = 200)
    private String motivo;
    
    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;
    
    @ManyToOne
    @JoinColumn(name = "id_medico")
    private Medico medico;
    
    public enum EstadoCita {
        PROGRAMADA, CANCELADA, COMPLETADA
    }
}