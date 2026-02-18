package com.hospital.hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hospital.hospital.model.entity.Paciente;
import com.hospital.hospital.model.repository.PacienteRepository;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repo;

    public Paciente obtenerPorId(Integer id) {
        return repo.obtenerConUsuario(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
    }

    public Paciente actualizar(Integer id, Paciente pacienteActualizado) {

        Paciente paciente = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        paciente.setNombre(pacienteActualizado.getNombre());
        paciente.setApellido(pacienteActualizado.getApellido());
        paciente.setFechaNacimiento(pacienteActualizado.getFechaNacimiento());
        paciente.setSexo(pacienteActualizado.getSexo());
        paciente.setTelefono(pacienteActualizado.getTelefono());
        paciente.setTipoSangre(pacienteActualizado.getTipoSangre());

        return repo.save(paciente);
    }
}
