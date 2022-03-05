package com.bucapps.dentapp.models.repository;

import com.bucapps.dentapp.models.entity.Resenias;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReseniasRepository extends JpaRepository<Resenias, Long> {

    List<Resenias> getAllByDoctoresIdOrderByCreatedDateDesc(Long doctorId);
}
