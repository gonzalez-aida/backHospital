package com.hospital.hospital.controller;

import com.hospital.hospital.model.entity.Medico;
import com.hospital.hospital.service.MedicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/medico")
@RequiredArgsConstructor
public class MedicoController {

    private final MedicoService medicoService;

    @PostMapping("/registro")
    public ResponseEntity<?> registrarMedico(@RequestBody Map<String, Object> body) {
        try {
            Medico medico = medicoService.registrarMedico(
                (String) body.get("correo"),
                (String) body.get("contrasena"),
                (String) body.get("nombre"),
                (String) body.get("especialidad"),
                (String) body.get("cedulaProfesional")
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(medico);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", e.getMessage()));
        }
    }
}