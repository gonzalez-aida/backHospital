package com.hospital.hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.hospital.model.dto.PacienteDTO;
import com.hospital.hospital.model.entity.Paciente;
import com.hospital.hospital.model.entity.Usuario;
import com.hospital.hospital.model.repository.PacienteRepository;
import com.hospital.hospital.model.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    private final PacienteRepository pacienteRepository;

    public PacienteDTO obtenerPorId(Integer id) {
        Paciente paciente = pacienteRepository.obtenerConUsuario(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        PacienteDTO dto = new PacienteDTO();
        dto.setIdPaciente(paciente.getIdPaciente());
        dto.setNombre(paciente.getNombre());
        dto.setApPaterno(paciente.getApPaterno());
        dto.setApMaterno(paciente.getApMaterno());
        dto.setCurp(paciente.getCurp());
        dto.setNss(paciente.getNss());
        dto.setFechaNacimiento(paciente.getFechaNacimiento());
        dto.setSexo(paciente.getSexo().name());
        dto.setTipoSangre(paciente.getTipoSangre().name());
        dto.setTelefono(paciente.getTelefono());
        dto.setTelefonoEmergencias(paciente.getTelefonoEmergencias());
        dto.setFechaAlta(paciente.getFechaAlta());
        dto.setCorreo(paciente.getUsuario().getCorreo());

        if (paciente.getDireccion() != null) {
            PacienteDTO.DireccionDTO dirDTO = new PacienteDTO.DireccionDTO();
            dirDTO.setCalle(paciente.getDireccion().getCalle());
            dirDTO.setNumExt(paciente.getDireccion().getNumExt());
            dirDTO.setNumInt(paciente.getDireccion().getNumInt());
            dirDTO.setColonia(paciente.getDireccion().getColonia());
            dirDTO.setCp(paciente.getDireccion().getCp());
            dirDTO.setLocalidad(paciente.getDireccion().getLocalidad());
            dirDTO.setEstado(paciente.getDireccion().getEstado());
            dto.setDireccion(dirDTO);
        }

        if (paciente.getUnidadMedica() != null) {
            PacienteDTO.UnidadMedicaDTO umDTO = new PacienteDTO.UnidadMedicaDTO();
            umDTO.setIdUm(paciente.getUnidadMedica().getIdUm());
            umDTO.setClave(paciente.getUnidadMedica().getClave());
            umDTO.setNombre(paciente.getUnidadMedica().getNombre());
            umDTO.setMunicipio(paciente.getUnidadMedica().getMunicipio());
            umDTO.setEstado(paciente.getUnidadMedica().getEstado());
            umDTO.setDelegacion(paciente.getUnidadMedica().getDelegacion());
            dto.setUnidadMedica(umDTO);
        }

        return dto;
    }

    @Transactional
    public Paciente actualizar(Integer id, Paciente pacienteActualizado) {

        Paciente paciente = pacienteRepository.obtenerConUsuario(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        // Datos personales
        paciente.setNombre(pacienteActualizado.getNombre());
        paciente.setApPaterno(pacienteActualizado.getApPaterno());
        paciente.setApMaterno(pacienteActualizado.getApMaterno());
        paciente.setFechaNacimiento(pacienteActualizado.getFechaNacimiento());
        paciente.setSexo(pacienteActualizado.getSexo());
        paciente.setTipoSangre(pacienteActualizado.getTipoSangre());
        paciente.setTelefono(pacienteActualizado.getTelefono());
        paciente.setTelefonoEmergencias(pacienteActualizado.getTelefonoEmergencias());
        paciente.setCurp(pacienteActualizado.getCurp());
        paciente.setNss(pacienteActualizado.getNss());

        // Dirección
        paciente.setDireccion(pacienteActualizado.getDireccion());

        // Datos de usuario (correo y contraseña)
        Usuario usuario = paciente.getUsuario();
        if (pacienteActualizado.getUsuario() != null) {
            if (pacienteActualizado.getUsuario().getCorreo() != null) {
                usuario.setCorreo(pacienteActualizado.getUsuario().getCorreo());
            }
            if (pacienteActualizado.getUsuario().getContrasena() != null &&
                    !pacienteActualizado.getUsuario().getContrasena().isBlank()) {
                usuario.setContrasena(passwordEncoder.encode(
                        pacienteActualizado.getUsuario().getContrasena()));
            }
        }
        usuarioRepository.save(usuario);

        return pacienteRepository.save(paciente);
    }

    @Transactional
    public Paciente registrarPaciente(String correo, String contrasena, String nombre,
            String apPaterno, String apMaterno, LocalDate fechaNacimiento,
            Paciente.Sexo sexo, String telefono, Paciente.TipoSangre tipoSangre) {

        if (usuarioRepository.existsByCorreo(correo)) {
            throw new RuntimeException("El correo ya está registrado: " + correo);
        }

        Usuario usuario = new Usuario();
        usuario.setCorreo(correo);
        usuario.setContrasena(passwordEncoder.encode(contrasena));
        usuario.setRol(Usuario.Rol.PACIENTE);

        usuarioRepository.save(usuario);

        Paciente paciente = new Paciente();
        paciente.setNombre(nombre);
        paciente.setApPaterno(apPaterno);
        paciente.setApMaterno(apMaterno);
        paciente.setFechaNacimiento(fechaNacimiento);
        paciente.setSexo(sexo);
        paciente.setTelefono(telefono);
        paciente.setTipoSangre(tipoSangre);
        paciente.setUsuario(usuario);

        return pacienteRepository.save(paciente);
    }
}