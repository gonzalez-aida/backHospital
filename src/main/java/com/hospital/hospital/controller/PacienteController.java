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
                    (String) body.get("apPaterno"),
                    (String) body.get("apMaterno"),
                    LocalDate.parse((String) body.get("fechaNacimiento")),
                    Paciente.Sexo.valueOf((String) body.get("sexo")),
                    (String) body.get("telefono"),
                    Paciente.TipoSangre.valueOf((String) body.get("tipoSangre")));
            return ResponseEntity.status(HttpStatus.CREATED).body(paciente);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(service.obtenerPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
            @PathVariable Integer id,
            @RequestBody Paciente paciente) {
        try {
            return ResponseEntity.ok(service.actualizar(id, paciente));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", e.getMessage()));
        }
    }
}