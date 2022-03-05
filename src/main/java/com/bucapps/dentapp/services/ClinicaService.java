package com.bucapps.dentapp.services;

import com.bucapps.dentapp.models.entity.Clinica;
import com.bucapps.dentapp.models.entity.Doctor;
import com.bucapps.dentapp.models.repository.ClinicaRepository;
import com.bucapps.dentapp.models.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicaService {
    @Autowired
    private ClinicaRepository clinicaRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    public Clinica obtenerClinicaPorId(Long clinicaId) {
        return clinicaRepository.findById(clinicaId).get();
    }

    public List<Doctor> obtenerDoctoresPorClinica(Long id) {
        return doctorRepository.findAllByClinicaId(id);
    }

    public List<Clinica> obtenerClinicas() {
        return clinicaRepository.findAll(Sort.by("nombre"));
    }
}
