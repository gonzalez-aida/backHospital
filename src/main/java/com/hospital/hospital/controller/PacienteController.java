package com.hospital.hospital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.hospital.hospital.model.entity.Paciente;
import com.hospital.hospital.service.PacienteService;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService service;

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
