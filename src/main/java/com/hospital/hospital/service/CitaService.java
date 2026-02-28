package com.hospital.hospital.service;

import com.hospital.hospital.model.entity.Cita;
import com.hospital.hospital.model.entity.Medico;
import com.hospital.hospital.model.entity.Paciente;
import com.hospital.hospital.model.repository.CitaRepository;
import com.hospital.hospital.model.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CitaService {

    private final CitaRepository citaRepository;
    private final PacienteRepository pacienteRepository;

    // Determina el turno según la hora
    private Medico.Turno determinarTurno(LocalTime hora) {
        if (hora.isBefore(LocalTime.of(14, 0))) {
            return Medico.Turno.matutino;
        } else if (hora.isBefore(LocalTime.of(20, 0))) {
            return Medico.Turno.vespertino;
        } else {
            return Medico.Turno.nocturno;
        }
    }

    // Asigna el médico con menos citas en esa fecha y turno
    private Medico asignarMedico(LocalDate fecha, LocalTime hora) {
        Medico.Turno turno = determinarTurno(hora);
        List<Medico> medicos = citaRepository.findMedicosByTurno(turno);

        if (medicos.isEmpty()) {
            throw new RuntimeException("No hay médicos disponibles en ese horario");
        }

        return medicos.stream()
                .min((m1, m2) -> (int) (citaRepository.countByMedicoAndFecha(m1, fecha)
                        - citaRepository.countByMedicoAndFecha(m2, fecha)))
                .orElseThrow(() -> new RuntimeException("No se pudo asignar un médico"));
    }

    @Transactional
    public Cita agendarCita(Integer idPaciente, LocalDate fecha, LocalTime hora,
            String motivo, Cita.TipoCita tipo) {

        Paciente paciente = pacienteRepository.findById(idPaciente)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        Medico medico = asignarMedico(fecha, hora);

        Cita cita = new Cita();
        cita.setFecha(fecha);
        cita.setHora(hora);
        cita.setMotivo(motivo);
        cita.setTipo(tipo);
        cita.setEstado(Cita.EstadoCita.pendiente);
        cita.setPaciente(paciente);
        cita.setMedico(medico);
        cita.setFolio(generarFolio());

        return citaRepository.save(cita);
    }

    // Ver citas del paciente
    public List<Cita> obtenerCitasPorPaciente(Integer idPaciente) {
        return citaRepository.findByPacienteIdPaciente(idPaciente);
    }

    // Cancelar cita
    @Transactional
    public Cita cancelarCita(Integer idCita) {
        Cita cita = citaRepository.findById(idCita)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        if (cita.getEstado() == Cita.EstadoCita.completada) {
            throw new RuntimeException("No se puede cancelar una cita completada");
        }

        cita.setEstado(Cita.EstadoCita.cancelada);
        return citaRepository.save(cita);
    }

    private String generarFolio() {
        return "CITA-" + System.currentTimeMillis();
    }
}