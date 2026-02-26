package com.hospital.hospital.model.entity;


import com.hospital.hospital.model.converter.ListToStringConverter;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.math.BigDecimal;

@Entity
@Table(name = "signos_vitales")
public class SignosVitales {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sv")
    private Long idSignosVitales;

    @Column(name = "peso_kg", precision = 5, scale = 2, nullable = false)
    private BigDecimal pesoKg;

    @Column(name = "talla_m", precision = 4, scale = 2, nullable = false)
    private BigDecimal tallaM;    

    @Column(name = "presion_arterial", length = 10, nullable = false)
    private String presionArterial;

    @Column(name = "frecuencia_cardiaca", nullable = false)
    private Short frecuenciaCardiaca; 

    @Column(name = "frecuencia_respiratoria", nullable = false)
    private Short frecuenciaRespiratoria;    

    @Column(name = "temperatura", precision = 4, scale = 1, nullable = false)
    private BigDecimal temperatura;

    @Column(name = "spo2", nullable = false)
    private Byte spo2;  

    @Column(name = "glucosa", nullable = false)
    private Short glucosa;  

    @Column(name = "fecha_registro", nullable = false, updatable = false)
    private LocalDate fechaRegistro;         

    @Column(name = "id_cita")
    private Long idCita;

    public SignosVitales() {}

    @PrePersist
    protected void onCreate() {fechaRegistro = LocalDate.now();}

    public Long getIdSignosVitales() { return idSignosVitales; }
    public void setIdSignosVitales(Long idSignosVitales) { this.idSignosVitales = idSignosVitales; }

    public BigDecimal getPesoKg() { return pesoKg; }
    public void setPesoKg(BigDecimal pesoKg) { this.pesoKg = pesoKg; }

    public BigDecimal getTallaM() { return tallaM; }
    public void setTallaM(BigDecimal tallaM) { this.tallaM = tallaM; }

    public String getPresionArterial() { return presionArterial; }
    public void setPresionArterial(String presionArterial) { this.presionArterial = presionArterial; }

    public Short getFrecuenciaCardiaca() { return frecuenciaCardiaca; }
    public void setFrecuenciaCardiaca(Short frecuenciaCardiaca) { this.frecuenciaCardiaca = frecuenciaCardiaca; }

    public Short getFrecuenciaRespiratoria() { return frecuenciaRespiratoria; }
    public void setFrecuenciaRespiratoria(Short frecuenciaRespiratoria) { this.frecuenciaRespiratoria = frecuenciaRespiratoria; }

    public BigDecimal getTemperatura() { return temperatura; }
    public void setTemperatura(BigDecimal temperatura) { this.temperatura = temperatura; }

    public Byte getSpo2() { return spo2; }
    public void setSpo2(Byte spo2) { this.spo2 = spo2; }

    public Short getGlucosa() { return glucosa; }
    public void setGlucosa(Short glucosa) { this.glucosa = glucosa; }

    public LocalDate getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDate fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    public Long getIdCita() { return idCita; }
    public void setIdCita(Long idCita) { this.idCita = idCita; }

}