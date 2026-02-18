package com.hospital.hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    @Autowired
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    private final PacienteRepository pacienteRepository;

    public Paciente obtenerPorId(Integer id) {
        return pacienteRepository.obtenerConUsuario(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
    }

    public Paciente actualizar(Integer id, Paciente pacienteActualizado) {

        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        paciente.setNombre(pacienteActualizado.getNombre());
        paciente.setApellido(pacienteActualizado.getApellido());
        paciente.setFechaNacimiento(pacienteActualizado.getFechaNacimiento());
        paciente.setSexo(pacienteActualizado.getSexo());
        paciente.setTelefono(pacienteActualizado.getTelefono());
        paciente.setTipoSangre(pacienteActualizado.getTipoSangre());

        return pacienteRepository.save(paciente);
    }

    @Transactional
    public Paciente registrarPaciente(String correo, String contrasena, String nombre,
            String apellido, LocalDate fechaNacimiento,
            Paciente.Sexo sexo, String telefono, String tipoSangre) {

        if (usuarioRepository.existsByCorreo(correo)) {
            throw new RuntimeException("El correo ya está registrado: " + correo);
        }

        Usuario usuario = new Usuario();
        usuario.setCorreo(correo);
        usuario.setContrasena(passwordEncoder.encode(contrasena));
        usuario.setRol(Usuario.Rol.PACIENTE);
        usuario.setEstado(Usuario.Estado.ACTIVO);
        usuarioRepository.save(usuario);

        Paciente paciente = new Paciente();
        paciente.setNombre(nombre);
        paciente.setApellido(apellido);
        paciente.setFechaNacimiento(fechaNacimiento);
        paciente.setSexo(sexo);
        paciente.setTelefono(telefono);
        paciente.setTipoSangre(tipoSangre);
        paciente.setUsuario(usuario);

        return pacienteRepository.save(paciente);
    }
}
