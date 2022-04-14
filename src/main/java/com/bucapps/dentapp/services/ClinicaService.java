package com.bucapps.dentapp.services;

import com.bucapps.dentapp.models.dto.UbicacionesClinicaDto;
import com.bucapps.dentapp.models.dto.UbicacionesDoctoresDto;
import com.bucapps.dentapp.models.entity.Clinica;
import com.bucapps.dentapp.models.entity.Direccion;
import com.bucapps.dentapp.models.entity.Doctor;
import com.bucapps.dentapp.models.repository.ClinicaRepository;
import com.bucapps.dentapp.models.repository.DireccionRepository;
import com.bucapps.dentapp.models.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClinicaService {
    @Autowired
    private ClinicaRepository clinicaRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private DireccionRepository direccionRepository;

    public Clinica obtenerClinicaPorId(Long clinicaId) {
        return clinicaRepository.findById(clinicaId).get();
    }

    public List<Doctor> obtenerDoctoresPorClinica(Long id) {
        return doctorRepository.findAllByClinicaId(id);
    }

    public List<Clinica> obtenerClinicas() {
        return clinicaRepository.findAll(Sort.by("nombre"));
    }

    public Clinica crearOActualizarClinica(Clinica clinica) {

        Direccion direccion = direccionRepository.save(clinica.getDireccion());

        clinica.setDireccion(direccion);
        return clinicaRepository.save(clinica);
    }

    public List<UbicacionesClinicaDto> obtenerUbicacionesClinica() {
        List<UbicacionesClinicaDto> listadoUbicacionesClinicas = new ArrayList<>();

        for (Clinica d : clinicaRepository.findAll()) {
            UbicacionesClinicaDto ubicacionClinica = new UbicacionesClinicaDto();
            ubicacionClinica.setLat(d.getDireccion().getLat());
            ubicacionClinica.setLng(d.getDireccion().getLng());
            ubicacionClinica.setNombre(d.getNombre());
            ubicacionClinica.setCalle(d.getDireccion().getCalle());
            ubicacionClinica.setInterior(d.getDireccion().getInterior());

            List<UbicacionesDoctoresDto> ubicacionesDoctoreList = new ArrayList<>();
            for (Doctor dr : obtenerDoctoresPorClinica(d.getId())) {
                UbicacionesDoctoresDto drUbi = new UbicacionesDoctoresDto();
                drUbi.setId(dr.getId());
                drUbi.setNombre(dr.getNombre());
                drUbi.setPhoto(dr.getPhoto());
                drUbi.setTarifa(dr.getTarifa());
           //   drUbi.setDisponibilidadSemanal(dr.getDisponibilidadSemanal());

                ubicacionesDoctoreList.add(drUbi);
            }

            ubicacionClinica.setDoctores(ubicacionesDoctoreList);

            listadoUbicacionesClinicas.add(ubicacionClinica);
        }


        return listadoUbicacionesClinicas;
    }
}
