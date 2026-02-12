package com.hospital.hospital.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.hospital.model.entity.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente,Integer>{
    
}
