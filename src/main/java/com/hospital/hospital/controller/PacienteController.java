package com.hospital.hospital.controller;

import com.hospital.hospital.model.entity.Paciente;
import com.hospital.hospital.service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/paciente")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService service;

    @PostMapping("/registro")
    public ResponseEntity<?> registrarPaciente(@RequestBody Map<String, Object> body) {
        try {
            Paciente paciente = service.registrarPaciente(
                    (String) body.get("correo"),
                    (String) body.get("contrasena"),
                    (String) body.get("nombre"),
                    (String) body.get("apellido"),
                    LocalDate.parse((String) body.get("fechaNacimiento")),
                    Paciente.Sexo.valueOf((String) body.get("sexo")),
                    (String) body.get("telefono"),
                    (String) body.get("tipoSangre"));
            return ResponseEntity.status(HttpStatus.CREATED).body(paciente);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public Paciente obtenerPorId(@PathVariable Integer id) {
        return service.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public Paciente actualizar(
            @PathVariable Integer id,
            @RequestBody Paciente paciente) {

        return service.actualizar(id, paciente);
    }
}
