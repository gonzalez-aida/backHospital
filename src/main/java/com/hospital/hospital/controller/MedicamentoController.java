package com.hospital.hospital.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hospital.hospital.model.entity.Medicamento;
import com.hospital.hospital.service.MedicamentoService;

@RestController
@RequestMapping("/medicamentos")
@CrossOrigin(origins = "*")
public class MedicamentoController {

    private final MedicamentoService medicamentoService;

    public MedicamentoController(MedicamentoService medicamentoService) {
        this.medicamentoService = medicamentoService;
    }

    // método solo se usará para ERRORES o mensajes específicos
    private ResponseEntity<Object> generarRespuesta(Object data, String mensaje, HttpStatus status) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("status", status.value());
        respuesta.put("mensaje", mensaje);
        respuesta.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        if (data != null) {
            respuesta.put("data", data);
        }
        return new ResponseEntity<>(respuesta, status);
    }

    @PostMapping
    public ResponseEntity<Object> createMedicamento(@RequestBody Medicamento medicamento) {
        Medicamento nuevo = medicamentoService.saveMedicamento(medicamento); 
        return generarRespuesta(nuevo, "Medicamento creado exitosamente", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> getAllMedicamentos() {
        List<Medicamento> lista = medicamentoService.getAllMedicamentos(); 
        return generarRespuesta(lista, "Lista de medicamentos obtenida exitosamente", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getMedicamentoById(@PathVariable Long id) {
        Medicamento m = medicamentoService.getMedicamentoById(id);
        if (m != null) { 
            return generarRespuesta(m, "Medicamento encontrado", HttpStatus.OK);
        } 
        return generarRespuesta(null,"El medicamento con ID " + id + " no existe", HttpStatus.NOT_FOUND);
    } 

    @GetMapping("/receta/{idReceta}")
    public ResponseEntity<Object> getMedicamentosByReceta(@PathVariable Long idReceta) { 
        List<Medicamento> d = medicamentoService.getMedicamentosByReceta(idReceta);
        
        if (d != null && !d.isEmpty()) {
            return generarRespuesta(d, "Medicamentos encontrados para la receta " + idReceta, HttpStatus.OK);
        }
        
        return generarRespuesta(null, "La receta con ID " + idReceta + " no tiene medicamentos asociados", HttpStatus.NOT_FOUND);
    }       

    @PutMapping("/{id}")
    public ResponseEntity<Object> actualizar(@PathVariable Long id, @RequestBody Medicamento actualizado) {
        Medicamento updated = medicamentoService.updateMedicamento(id, actualizado);
        if (updated != null) { 
            return generarRespuesta(updated, "Medicamento actualizado exitosamente", HttpStatus.OK);
        } 
        return generarRespuesta(null, "No se pudo actualizar: ID " + id + " no encontrado", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMedicamento(@PathVariable Long id) {
        Medicamento d = medicamentoService.getMedicamentoById(id);
        if (d != null) {
            medicamentoService.deleteMedicamento(id);
            return generarRespuesta(null, "Medicamento eliminado correctamente", HttpStatus.OK);
        } 
        return generarRespuesta(null, "Error al eliminar: ID " + id + " no existe", HttpStatus.NOT_FOUND);
    }
}