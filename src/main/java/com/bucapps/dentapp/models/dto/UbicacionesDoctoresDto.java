package com.bucapps.dentapp.models.dto;

import com.bucapps.dentapp.models.entity.DisponibilidadSemanal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UbicacionesDoctoresDto {
    private Long id;
    private String nombre;
    private String photo;
    private Double tarifa;
    private DisponibilidadSemanal disponibilidadSemanal;
}
