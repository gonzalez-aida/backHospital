package com.hospital.hospital.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "expediente")
public class Expediente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_expediente")
    private Integer idExpediente;
    
    @Column(name = "alergias", columnDefinition = "TEXT")
    private String alergias;
    
    @Column(name = "antecedentes", columnDefinition = "TEXT")
    private String antecedentes;
    
    @Column(name = "observaciones", columnDefinition = "TEXT")
    private String observaciones;
    
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    
    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;
}