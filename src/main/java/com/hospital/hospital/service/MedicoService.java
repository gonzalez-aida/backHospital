package com.hospital.hospital.service;

import com.hospital.hospital.model.entity.Medico;
import com.hospital.hospital.model.entity.Usuario;
import com.hospital.hospital.model.repository.MedicoRepository;
import com.hospital.hospital.model.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MedicoService {

    private final UsuarioRepository usuarioRepository;
    private final MedicoRepository medicoRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Medico registrarMedico(String correo, String contrasena, String nombre,
                                   String especialidad, String cedulaProfesional) {

        if (usuarioRepository.existsByCorreo(correo)) {
            throw new RuntimeException("El correo ya está registrado: " + correo);
        }

        if (medicoRepository.existsByCedulaProfesional(cedulaProfesional)) {
            throw new RuntimeException("La cédula profesional ya está registrada: " + cedulaProfesional);
        }

        Usuario usuario = new Usuario();
        usuario.setCorreo(correo);
        usuario.setContrasena(passwordEncoder.encode(contrasena));
        usuario.setRol(Usuario.Rol.MEDICO);
        usuario.setEstado(Usuario.Estado.ACTIVO);
        usuarioRepository.save(usuario);

        Medico medico = new Medico();
        medico.setNombre(nombre);
        medico.setEspecialidad(especialidad);
        medico.setCedulaProfesional(cedulaProfesional);
        medico.setUsuario(usuario);

        return medicoRepository.save(medico);
    }
}