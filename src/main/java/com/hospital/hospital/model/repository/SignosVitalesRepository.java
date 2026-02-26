package com.hospital.hospital.model.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; 
import com.hospital.hospital.model.entity.SignosVitales;

@Repository
public interface SignosVitalesRepository extends JpaRepository<SignosVitales, Long> {
    List<SignosVitales> findByIdCita(Long idCita);
}