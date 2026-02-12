package com.hospital.hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.hospital.model.repository.ExpedienteRepository;

@Service
public class ExpedienteService {

    @Autowired
    private ExpedienteRepository repo;
    
}
