package com.hospital.hospital.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.hospital.model.repository.MedicamentoRepository;
import com.hospital.hospital.model.entity.Medicamento;

@Service
public class MedicamentoService {

    // @Autowired
    private final MedicamentoRepository medicamentoRepository;
    public MedicamentoService(MedicamentoRepository medicamentoRepository) {
        this.medicamentoRepository = medicamentoRepository;
    } //constructor para inyectar el repositorio
    
    public Medicamento saveMedicamento(Medicamento medicamento) {
        return medicamentoRepository.save(medicamento);
    } //guardar medicamento
    
    public List<Medicamento> getAllMedicamentos() {
        return medicamentoRepository.findAll();
    } //obtener todos los medicamentos

    public Medicamento getMedicamentoById(Long id_medicamento) {
        return medicamentoRepository.findById(id_medicamento).orElse(null);
    } //obtener medicamento por id

    public List<Medicamento> getMedicamentosByReceta(Long idReceta) {
        return medicamentoRepository.findByIdReceta(idReceta);
    } //obtener medicamentos por id de receta

    public void deleteMedicamento(Long id_medicamento) {
        medicamentoRepository.deleteById(id_medicamento);
    } //eliminar medicamento por id

    public Medicamento updateMedicamento(Long id, Medicamento actualizado) {
        return medicamentoRepository.findById(id).map(existente -> {

            if (actualizado.getNombre() != null) {
                existente.setNombre(actualizado.getNombre());
            }

            if (actualizado.getPresentacion() != null) {
                existente.setPresentacion(actualizado.getPresentacion());
            }

            if (actualizado.getDosis() != null) {
                existente.setDosis(actualizado.getDosis());
            }

            if (actualizado.getFrecuencia() != null) {
                existente.setFrecuencia(actualizado.getFrecuencia());
            }

            if (actualizado.getDuracion() != null) {
                existente.setDuracion(actualizado.getDuracion());
            }

            if (actualizado.getCantidad() != null) {
                existente.setCantidad(actualizado.getCantidad());
            }

            if (actualizado.getVia() != null) {
                existente.setVia(actualizado.getVia());
            }

            if (actualizado.getIdReceta() != null) {
                existente.setIdReceta(actualizado.getIdReceta());
            }

            return medicamentoRepository.save(existente);

        }).orElse(null);
    }
}