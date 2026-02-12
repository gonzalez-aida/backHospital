package com.hospital.hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.hospital.model.repository.PacienteRepository;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repo;
    
}
