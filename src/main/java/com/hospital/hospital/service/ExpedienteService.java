package com.hospital.hospital.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.hospital.model.repository.ExpedienteRepository;
import com.hospital.hospital.model.entity.Expediente;

@Service
public class ExpedienteService {

    // @Autowired
    private final ExpedienteRepository expedienteRepository;
    public ExpedienteService(ExpedienteRepository expedienteRepository) {
        this.expedienteRepository = expedienteRepository;
    } //constructor para inyectar el repositorio
    
    public Expediente saveExpediente(Expediente expediente) {
        return expedienteRepository.save(expediente);
    } //guardar expediente
    
    public List<Expediente> getAllExpedientes() {
        return expedienteRepository.findAll();
    } //obtener todos los expedientes

    public Expediente getExpedienteById(Long id_expediente) {
        return expedienteRepository.findById(id_expediente).orElse(null);
    } //obtener expediente por id

    public List<Expediente> getExpedientesByPaciente(Long idPaciente) {
        return expedienteRepository.findByIdPaciente(idPaciente);
    } //obtener expedientes por id de paciente

    public void deleteExpediente(Long id_expediente) {
        expedienteRepository.deleteById(id_expediente);
    } //eliminar expediente por id

    public Expediente updateExpediente(Long id, Expediente actualizado) {
        return expedienteRepository.findById(id).map(existente -> {

            if (actualizado.getFolio() != null) {
                existente.setFolio(actualizado.getFolio());
            }

            if (actualizado.getAnt_heredofamiliares() != null) {
                existente.setAnt_heredofamiliares(actualizado.getAnt_heredofamiliares());
            }

            if (actualizado.getAnt_patologicos() != null) {
                existente.setAnt_patologicos(actualizado.getAnt_patologicos());
            }

            if (actualizado.getAnt_quirurgicos() != null) {
                existente.setAnt_quirurgicos(actualizado.getAnt_quirurgicos());
            }

            if (actualizado.getAnt_alergicos() != null) {
                existente.setAnt_alergicos(actualizado.getAnt_alergicos());
            }

            if (actualizado.getAnt_cronicas() != null) {
                existente.setAnt_cronicas(actualizado.getAnt_cronicas());
            }

            if (actualizado.getAnt_ginecoobstetricos() != null) {
                existente.setAnt_ginecoobstetricos(actualizado.getAnt_ginecoobstetricos());
            }

            if (actualizado.getObservaciones() != null) {
                existente.setObservaciones(actualizado.getObservaciones());
            }

            if (actualizado.getIdPaciente() != null) {
                existente.setIdPaciente(actualizado.getIdPaciente());
            }

            if (actualizado.getIdMedico() != null) {
                existente.setIdMedico(actualizado.getIdMedico());
            }

            return expedienteRepository.save(existente);

        }).orElse(null);
    }
}