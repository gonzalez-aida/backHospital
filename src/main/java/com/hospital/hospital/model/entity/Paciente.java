package com.hospital.hospital.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "paciente")
public class Paciente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paciente")
    private Integer idPaciente;
    
    @Column(name = "nombre", length = 100)
    private String nombre;
    
    @Column(name = "apellido", length = 100)
    private String apellido;
    
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "sexo")
    private Sexo sexo;
    
    @Column(name = "telefono", length = 15)
    private String telefono;
    
    @Column(name = "tipo_sangre", length = 5)
    private String tipoSangre;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario", unique = true)
    private Usuario usuario;
    
    public enum Sexo {
        M, F
    }
}