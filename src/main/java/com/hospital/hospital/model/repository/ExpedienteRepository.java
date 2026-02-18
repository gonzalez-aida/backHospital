package com.hospital.hospital.model.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; 
import com.hospital.hospital.model.entity.Expediente;

@Repository
public interface ExpedienteRepository extends JpaRepository<Expediente, Long> {
    List<Expediente> findByIdMedico(Long idMedico);
    List<Expediente> findByIdPaciente(Long idPaciente);
}