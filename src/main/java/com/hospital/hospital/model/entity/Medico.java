package com.hospital.hospital.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "medico")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medico")
    private Integer idMedico;

    @Column(name = "num_empleado", unique = true, length = 20)
    private String numEmpleado;

    @Column(name = "cedula_profesional", unique = true, length = 20)
    private String cedulaProfesional;

    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "ap_paterno", length = 100)
    private String apPaterno;

    @Column(name = "ap_materno", length = 100)
    private String apMaterno;

    @Column(name = "especialidad", length = 100)
    private String especialidad;

    @Enumerated(EnumType.STRING)
    @Column(name = "turno")
    private Turno turno;

    @Column(name = "consultorio", length = 10)
    private String consultorio;

    @Column(name = "activo")
    private Boolean activo = true;

    @ManyToOne
    @JoinColumn(name = "id_usuario", unique = true)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_um")
    private UnidadMedica unidadMedica; 

    public enum Turno {
        matutino, vespertino, nocturno, jornada_acumulada
    }
}