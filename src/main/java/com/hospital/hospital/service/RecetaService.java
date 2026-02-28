package com.hospital.hospital.service;

import com.hospital.hospital.model.dto.RecetaDTO;
import com.hospital.hospital.model.entity.Medicamento;
import com.hospital.hospital.model.entity.Receta;
import com.hospital.hospital.model.repository.MedicamentoRepository;
import com.hospital.hospital.model.repository.RecetaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecetaService {

    private final RecetaRepository recetaRepository;
    private final MedicamentoRepository medicamentoRepository;

    public List<RecetaDTO> obtenerRecetasPorPaciente(Integer idPaciente) {
        List<Receta> recetas = recetaRepository.findByPacienteId(idPaciente);

        return recetas.stream().map(receta -> {
            RecetaDTO dto = new RecetaDTO();
            dto.setIdReceta(receta.getIdReceta());
            dto.setFolio(receta.getFolio());
            dto.setFecha(receta.getFecha());
            dto.setVencimiento(receta.getVencimiento());
            dto.setEstado(receta.getEstado().name());

            List<RecetaDTO.MedicamentoDTO> medicamentos = medicamentoRepository
                    .findByIdReceta(Long.valueOf(receta.getIdReceta()))
                    .stream().map(m -> {
                        RecetaDTO.MedicamentoDTO medDTO = new RecetaDTO.MedicamentoDTO();
                        medDTO.setNombre(m.getNombre());
                        medDTO.setPresentacion(m.getPresentacion());
                        medDTO.setDosis(m.getDosis());
                        medDTO.setFrecuencia(m.getFrecuencia());
                        medDTO.setDuracion(m.getDuracion());
                        medDTO.setCantidad(m.getCantidad());
                        medDTO.setVia(m.getVia().name());
                        return medDTO;
                    }).toList();

            dto.setMedicamentos(medicamentos);
            return dto;
        }).toList();
    }
}