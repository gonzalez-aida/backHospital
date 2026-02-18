package com.hospital.hospital.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.hospital.model.entity.Medico;

public interface MedicoRepository extends JpaRepository<Medico,Integer>{
    boolean existsByCedulaProfesional(String cedulaProfesional);
    
}
