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
    
    @Column(name = "nombre", length = 100)
    private String nombre;
    
    @Column(name = "especialidad", length = 100)
    private String especialidad;
    
    @Column(name = "cedula_profesional", unique = true, length = 50)
    private String cedulaProfesional;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario", unique = true)
    private Usuario usuario;
}