package com.hospital.hospital.model.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "expediente")
public class Expediente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_expediente")
    private Long idExpediente;
    
    @ElementCollection
    @CollectionTable(name = "expediente_alergias", joinColumns = @JoinColumn(name = "id_expediente"))
    @Column(name = "alergias")
    private List<String> alergias;

    @ElementCollection
    @CollectionTable(name = "expediente_antecedentes", joinColumns = @JoinColumn(name = "id_expediente"))
    @Column(name = "antecedentes")
    private List<String> antecedentes;

    @ElementCollection
    @CollectionTable(name = "expediente_observaciones", joinColumns = @JoinColumn(name = "id_expediente"))
    @Column(name = "observaciones")
    private List<String> observaciones;
    
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    
    @Column(name = "id_paciente")
    private Long idPaciente;

    @Column(name = "id_medico")
    private Long idMedico;

    public Expediente() {}

    public Long getIdExpediente() { return idExpediente; }
    public void setIdExpediente(Long idExpediente) { this.idExpediente = idExpediente; }

    public String getAlergias() { return alergias; }
    public void setAlergias(String alergias) { this.alergias = alergias; }

    public String getAntecedentes() { return antecedentes; }
    public void setAntecedentes(String antecedentes) { this.antecedentes = antecedentes; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public Long getIdPaciente() { return idPaciente; }
    public void setIdPaciente(Long idPaciente) { this.idPaciente = idPaciente; }

    public Long getIdMedico() { return idMedico; }
    public void setIdMedico(Long idMedico) { this.idMedico = idMedico; }     
}