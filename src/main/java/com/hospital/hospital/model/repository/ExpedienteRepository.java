package com.hospital.hospital.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; // <-- FALTABA ESTA IMPORTACIÓN
import com.hospital.hospital.model.entity.Expediente;

@Repository
public interface ExpedienteRepository extends JpaRepository<Expediente, Long> {
}