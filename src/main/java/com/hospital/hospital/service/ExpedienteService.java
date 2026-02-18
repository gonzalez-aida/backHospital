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

    public List<Expediente> getExpedientesByMedico(Long idMedico) {
        return expedienteRepository.findByIdMedico(idMedico);
    } //obtener expedientes por id de médico

    public List<Expediente> getExpedientesByPaciente(Long idPaciente) {
        return expedienteRepository.findByIdPaciente(idPaciente);
    } //obtener expedientes por id de paciente

    public void deleteExpediente(Long id_expediente) {
        expedienteRepository.deleteById(id_expediente);
    } //eliminar expediente por id

    public Expediente updateExpediente(Long id, Expediente actualizado) {
        return expedienteRepository.findById(id).map(existente -> {
            // Solo actualiza si el campo enviado NO es nulo
            if (actualizado.getAlergias() != null) {
                existente.setAlergias(actualizado.getAlergias());
            }
            if (actualizado.getAntecedentes() != null) {
                existente.setAntecedentes(actualizado.getAntecedentes());
            }
            if (actualizado.getObservaciones() != null) {
                existente.setObservaciones(actualizado.getObservaciones());
            }
            if (actualizado.getIdMedico() != null) {
                existente.setIdMedico(actualizado.getIdMedico());
            }
            // No tocamos la fecha de creación ni el idPaciente si no vienen
            
            return expedienteRepository.save(existente);
        }).orElse(null);
    }
        
}
