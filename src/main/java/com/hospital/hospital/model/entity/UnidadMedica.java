package com.hospital.hospital.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "unidad_medica")
public class UnidadMedica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_um")
    private Short idUm;

    @Column(name = "clave", unique = true, length = 20)
    private String clave;

    @Column(name = "nombre", length = 150)
    private String nombre;

    @Column(name = "nivel")
    private Byte nivel;

    @Column(name = "delegacion", length = 80)
    private String delegacion;

    @Column(name = "municipio", length = 80)
    private String municipio;

    @Column(name = "estado", length = 50)
    private String estado;
}
