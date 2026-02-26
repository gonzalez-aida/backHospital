package com.hospital.hospital.model.dto;

import com.hospital.hospital.model.entity.Medico.Turno;
import lombok.Data;

@Data
public class MedicoDTO {
    private String numEmpleado;
    private String cedulaProfesional;
    private String nombre;
    private String apPaterno;
    private String apMaterno;
    private String especialidad;
    private Turno turno;
    private String consultorio;
    private Short idUm; 
}