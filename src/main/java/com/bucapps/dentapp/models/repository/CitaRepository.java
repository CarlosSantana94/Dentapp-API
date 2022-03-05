package com.bucapps.dentapp.models.repository;

import com.bucapps.dentapp.models.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {

    List<Cita> getAllByDoctorIdAndFecha(Long doctorId, Date fecha);

    Cita getAllByDoctorIdAndUsuarioTokenAndApartada(Long doctorId, String usuarioId, Boolean apartada);
}
