package com.hospital.hospital.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "direccion")
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_direccion")
    private Integer idDireccion;

    @Column(name = "calle", length = 200)
    private String calle;

    @Column(name = "num_ext", length = 200)
    private String numExt;

    @Column(name = "num_int", length = 200)
    private String numInt;

    @Column(name = "colonia", length = 200)
    private String colonia;

    @Column(name = "cp", length = 200)
    private String cp;

    @Column(name = "localidad", length = 200)
    private String localidad;

    @Column(name = "estado", length = 200)
    private String estado;
}