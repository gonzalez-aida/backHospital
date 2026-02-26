package com.hospital.hospital.model.entity;


import com.hospital.hospital.model.converter.ListToStringConverter;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "expediente")
public class Expediente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_expediente")
    private Long idExpediente;
    
    private String alergias;
    private String antecedentes;
    private String observaciones;
    
    @Column(name = "fecha_apertura", nullable = false, updatable = false)
    private LocalDate fechaApertura;

    @Column(name = "ultima_actualizacion", nullable = false)
    private LocalDateTime fechaActualizacion;
    
    @Column(name = "id_paciente")
    private Long idPaciente;

    @Column(name = "id_medico")
    private Long idMedico;

    public Expediente() {}

    @PrePersist
    protected void onCreate() {fechaApertura = LocalDate.now(); fechaActualizacion = LocalDateTime.now();}

    @PreUpdate
    protected void onUpdate() {fechaActualizacion = LocalDateTime.now();}

    public Long getIdExpediente() { return idExpediente; }
    public void setIdExpediente(Long idExpediente) { this.idExpediente = idExpediente; }

    public String getFolio() { return folio; }
    public void setFolio(String folio) { this.folio = folio; }

    public String getAnt_heredofamiliares() { return ant_heredofamiliares; }
    public void setAnt_heredofamiliares(String ant_heredofamiliares) { this.ant_heredofamiliares = ant_heredofamiliares; }

    public String getAnt_patologicos() { return ant_patologicos; }
    public void setAnt_patologicos(String ant_patologicos) { this.ant_patologicos = ant_patologicos; }

    public String getAnt_quirurgicos() { return ant_quirurgicos; }
    public void setAnt_quirurgicos(String ant_quirurgicos) { this.ant_quirurgicos = ant_quirurgicos; }

    public String getAnt_alergicos() { return ant_alergicos; }
    public void setAnt_alergicos(String ant_alergicos) { this.ant_alergicos = ant_alergicos; }

    public String getAnt_cronicas() { return enf_cronicas; }
    public void setAnt_cronicas(String enf_cronicas) { this.enf_cronicas = enf_cronicas; }

    public String getAnt_ginecoobstetricos() { return ant_ginecoobstetricos; }
    public void setAnt_ginecoobstetricos(String ant_ginecoobstetricos) { this.ant_ginecoobstetricos = ant_ginecoobstetricos; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public LocalDate getFechaApertura() {return fechaApertura;}
    public void setFechaApertura(LocalDate fechaApertura) {this.fechaApertura = fechaApertura;}

    public LocalDateTime getFechaActualizacion() {return fechaActualizacion;}
    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {this.fechaActualizacion = fechaActualizacion;}

    public Long getIdPaciente() { return idPaciente; }
    public void setIdPaciente(Long idPaciente) { this.idPaciente = idPaciente; }

    public Long getIdMedico() { return idMedico; }
    public void setIdMedico(Long idMedico) { this.idMedico = idMedico; }

}