package com.bucapps.dentapp.services;

import com.bucapps.dentapp.models.dto.PromedioReseniaDto;
import com.bucapps.dentapp.models.entity.Resenias;
import com.bucapps.dentapp.models.repository.ReseniasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReseniasService {

    @Autowired
    private ReseniasRepository reseniasRepository;

    public PromedioReseniaDto obtenerReseniaDelDr(Long doctorId) {
        List<Resenias> resenias = obtenerTodasLasReseniasPorDr(doctorId);

        Long sumatoria = 0L;
        Long cuantos = 0L;
        for (Resenias r : resenias) {
            sumatoria += r.getEstrellas();
            cuantos++;
        }


        PromedioReseniaDto dto = new PromedioReseniaDto();
        dto.setCantidadComentarios(cuantos);
        if (cuantos > 0) {
            dto.setEstrellas((sumatoria * 5) / (cuantos * 5));
        }

        return dto;

    }

    public List<Resenias> obtenerTodasLasReseniasPorDr(Long doctorId) {
        return reseniasRepository.getAllByDoctoresIdOrderByCreatedDateDesc(doctorId);
    }
}
