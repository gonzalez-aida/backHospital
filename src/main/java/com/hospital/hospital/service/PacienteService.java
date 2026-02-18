package com.hospital.hospital.service;

import com.hospital.hospital.model.entity.Paciente;
import com.hospital.hospital.model.entity.Usuario;
import com.hospital.hospital.model.repository.PacienteRepository;
import com.hospital.hospital.model.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final UsuarioRepository usuarioRepository;
    private final PacienteRepository pacienteRepository;
    private final PasswordEncoder passwordEncoder;

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