package com.hospital.hospital.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hospital.hospital.model.entity.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

    @Query("SELECT p FROM Paciente p INNER JOIN FETCH p.usuario WHERE p.idPaciente = :id")
    Optional<Paciente> obtenerConUsuario(@Param("id") Integer id);
}
