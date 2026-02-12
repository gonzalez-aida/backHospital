package com.hospital.hospital.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.hospital.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{
    
}
