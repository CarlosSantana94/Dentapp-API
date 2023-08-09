package com.bucapps.dentapp.services;

import com.bucapps.dentapp.mail.EmailSender;
import com.bucapps.dentapp.models.dto.DoctorLogin;
import com.bucapps.dentapp.models.entity.DisponibilidadSemanal;
import com.bucapps.dentapp.models.entity.Doctor;
import com.bucapps.dentapp.models.repository.DisponibilidadSemanalRepository;
import com.bucapps.dentapp.models.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

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

        if (!doctor.getYaSeRegistro()) {
            // no se ha registrado o completado el cambio de contraseña
            UUID uuid = UUID.randomUUID();
            doctor.setTempPwd(uuid.toString());
            EmailSender.sendEmail(doctor.getEmail(), "Bienvenido a Dentapp", "Usa este texto como tu contraseña de primer uso: " + uuid.toString());
        }

        return doctorRepository.save(doctor);
    }


    public DoctorLogin loginDoctor(DoctorLogin doctorLogin) {

        Doctor doctor = doctorRepository.findDoctorByEmail(doctorLogin.getEmail());

        if (doctor == null) {
            doctorLogin.setMensaje("Error en los datos de acceso");
        } else {
            doctorLogin.setNombre(doctor.getNombre());
            doctorLogin.setId(doctor.getId());

            if (doctor.getYaSeRegistro() && Objects.equals(doctor.getPwd(), doctorLogin.getPassword())) {
                //login normal ya creado
                doctorLogin.setMensaje("Bienvenido!");

            } else if (!doctor.getYaSeRegistro() && Objects.equals(doctorLogin.getPassword(), doctor.getTempPwd())) {
                // login nuevo registro
                doctorLogin.setMensaje("Confirme su registro.");


            } else {
                doctorLogin.setMensaje("Error en los datos de acceso.");
            }
        }


        return doctorLogin;
    }

    public DoctorLogin actualizarPwd(DoctorLogin doctorLogin) {

        Doctor doctor = doctorRepository.findById(doctorLogin.getId()).get();

        doctor.setPwd(doctorLogin.getPassword());
        doctor.setTempPwd(new Date().toString());
        doctor.setYaSeRegistro(true);

        doctorRepository.save(doctor);

        return doctorLogin;
    }
}
