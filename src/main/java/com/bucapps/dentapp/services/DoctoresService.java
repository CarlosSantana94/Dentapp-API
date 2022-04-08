package com.bucapps.dentapp.services;

import com.bucapps.dentapp.models.entity.DisponibilidadSemanal;
import com.bucapps.dentapp.models.entity.Doctor;
import com.bucapps.dentapp.models.repository.DisponibilidadSemanalRepository;
import com.bucapps.dentapp.models.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctoresService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private DisponibilidadSemanalRepository disponibilidadSemanalRepository;

    public List<Doctor> obtenerTodosLosDoctores() {
        return doctorRepository.findAll(Sort.by("nombre"));
    }

    public Doctor obtenerDoctorPorId(Long id) {
        return doctorRepository.findById(id).get();
    }

    public Doctor crearDoctor(Doctor doctor) {
        DisponibilidadSemanal disp = disponibilidadSemanalRepository.save(doctor.getDisponibilidadSemanal());

        doctor.setDisponibilidadSemanal(disp);
        return doctorRepository.save(doctor);
    }


}
