package com.hospital.hospital.model.entity;


import com.hospital.hospital.model.converter.ListToStringConverter;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList; 
import com.hospital.hospital.model.enums.MedicamentoVia;

@Entity
@Table(name = "medicamento")
public class Medicamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medicamento")
    private Long idMedicamento;

    @Column(name = "nombre", length = 150, nullable = false, unique = true)
    private String nombre;

    @Column(name = "presentacion", length = 80, nullable = false, unique = true)
    private String presentacion; 

    @Column(name = "dosis", length = 50, nullable = false, unique = true)
    private String dosis; 

    @Column(name = "frecuencia", length = 80, nullable = false, unique = true)
    private String frecuencia;   

    @Column(name = "duracion", length = 40, nullable = false, unique = true)
    private String duracion;  

    @Column(name = "cantidad", nullable = false)
    private Short cantidad;             

    @Enumerated(EnumType.STRING)
    @Column(name = "via")
    private MedicamentoVia via;

    @Column(name = "id_receta")
    private Integer idReceta; 


    public Medicamento() {}

    public Long getIdMedicamento() { return idMedicamento; }
    public void setIdMedicamento(Long idMedicamento) { this.idMedicamento = idMedicamento; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPresentacion() { return presentacion; }
    public void setPresentacion(String presentacion) { this.presentacion = presentacion; }

    public String getDosis() { return dosis; }
    public void setDosis(String dosis) { this.dosis = dosis; }

    public String getFrecuencia() { return frecuencia; }
    public void setFrecuencia(String frecuencia) { this.frecuencia = frecuencia; }

    public String getDuracion() { return duracion; }
    public void setDuracion(String duracion) { this.duracion = duracion; }

    public Short getCantidad() { return cantidad; }
    public void setCantidad(Short cantidad) { this.cantidad = cantidad; }

    public MedicamentoVia getVia() { return via; }
    public void setVia(MedicamentoVia via) { this.via = via; }

    public Integer getIdReceta() { return idReceta; }
    public void setIdReceta(Integer idReceta) { this.idReceta = idReceta; }

}