package com.hospital.hospital.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hospital.hospital.model.entity.SignosVitales;
import com.hospital.hospital.service.SignosVitalesService;

@RestController
@RequestMapping("/signos-vitales")
@CrossOrigin(origins = "*")
public class SignosVitalesController {

    private final SignosVitalesService signosVitalesService;

    public SignosVitalesController(SignosVitalesService signosVitalesService) {
        this.signosVitalesService = signosVitalesService;
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
    public ResponseEntity<Object> createSignosVitales(@RequestBody SignosVitales signosVitales) {
        SignosVitales nuevo = signosVitalesService.saveSignosVitales(signosVitales); 
        return generarRespuesta(nuevo, "Signos Vitales creados exitosamente", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> getAllSignosVitales() {
        List<SignosVitales> lista = signosVitalesService.getAllSignosVitales(); 
        return generarRespuesta(lista, "Lista de signos vitales obtenida exitosamente", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSignosVitalesById(@PathVariable Long id) {
        SignosVitales sv = signosVitalesService.getSignosVitalesById(id);
        if (sv != null) {
            // ÉXITO: Envía solo los datos del expediente
            return generarRespuesta(sv, "Signos Vitales encontrados", HttpStatus.OK);
        }
        // ERROR: Envía mensaje y estatus
        return generarRespuesta(null,"Los signos vitales con ID " + id + " no existen", HttpStatus.NOT_FOUND);
    } 

    @GetMapping("/cita/{idCita}")
    public ResponseEntity<Object> getSignosVitalesByCita(@PathVariable Long idCita) { 
        List<SignosVitales> sv = signosVitalesService.getSignosVitalesByCita(idCita);
        
        if (sv != null && !sv.isEmpty()) {
            return generarRespuesta(sv, "Signos Vitales encontrados para la cita " + idCita, HttpStatus.OK);
        }
        
        return generarRespuesta(null, "La cita con ID " + idCita + " no tiene signos vitales asociados", HttpStatus.NOT_FOUND);
    }       

    @PutMapping("/{id}")
    public ResponseEntity<Object> actualizar(@PathVariable Long id, @RequestBody SignosVitales actualizado) {
        SignosVitales updated = signosVitalesService.updateSignosVitales(id, actualizado);
        if (updated != null) {
            // ÉXITO: Envía solo los datos actualizados
            return generarRespuesta(updated, "Signos Vitales actualizados exitosamente", HttpStatus.OK);
        }
        // ERROR: Envía mensaje y estatus
        return generarRespuesta(null, "No se pudo actualizar: ID " + id + " no encontrado", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSignosVitales(@PathVariable Long id) {
        SignosVitales sv = signosVitalesService.getSignosVitalesById(id);
        if (sv != null) {
            signosVitalesService.deleteSignosVitales(id);
            // ÉXITO: En un delete exitoso, solemos enviar un mensaje de confirmación
            return generarRespuesta(null, "Signos Vitales eliminados correctamente", HttpStatus.OK);
        }
        // ERROR: Envía mensaje y estatus
        return generarRespuesta(null, "Error al eliminar: ID " + id + " no existe", HttpStatus.NOT_FOUND);
    }
}