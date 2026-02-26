package com.hospital.hospital.model.repository;

import com.hospital.hospital.model.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medico, Integer> {
    boolean existsByCedulaProfesional(String cedulaProfesional);
    boolean existsByNumEmpleado(String numEmpleado);
    Optional<Medico> findByUsuarioIdUsuario(Integer idUsuario);
}
