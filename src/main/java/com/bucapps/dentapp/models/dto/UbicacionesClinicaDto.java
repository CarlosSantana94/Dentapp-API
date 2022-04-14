package com.bucapps.dentapp.models.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UbicacionesClinicaDto {
    private String lat;
    private String lng;
    private String nombre;
    private String calle;
    private String interior;
    private List<UbicacionesDoctoresDto> doctores;
}
