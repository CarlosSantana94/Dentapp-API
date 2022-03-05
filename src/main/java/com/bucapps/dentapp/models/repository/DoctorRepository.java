package com.bucapps.dentapp.models.repository;

import com.bucapps.dentapp.models.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    List<Doctor> findAllByClinicaId(Long clinicaId);

}
