package com.hospital.hospital.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paciente")
    private Integer idPaciente;

    @Column(name = "nss", length = 11, nullable = false, unique = true)
    private String nss;

    @Column(name = "curp", length = 18, nullable = false, unique = true)
    private String curp;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "ap_paterno", length = 100, nullable = false)
    private String apPaterno;

    @Column(name = "ap_materno", length = 100, nullable = false)
    private String apMaterno;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Enumerated(EnumType.STRING)
    @Column(name = "sexo", nullable = false)
    private Sexo sexo;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_sangre")
    private TipoSangre tipoSangre;

    @Column(name = "telefono", length = 15)
    private String telefono;

    @Column(name = "tel_emergencia", length = 150)
    private String telefonoEmergencias;

    @Column(name = "fecha_alta")
    private LocalDateTime fechaAlta;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_direccion")
    private Direccion direccion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_um")
    private UnidadMedica unidadMedica;

    public enum Sexo {
        M, F
    }

    public enum TipoSangre {
        A_POS, A_NEG, B_POS, B_NEG, AB_POS, AB_NEG, O_POS, O_NEG
    }
}