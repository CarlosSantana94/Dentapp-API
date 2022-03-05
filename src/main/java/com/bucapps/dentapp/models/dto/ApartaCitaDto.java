package com.bucapps.dentapp.models.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class ApartaCitaDto {
    private Long id;
    private Long doctorId;
    private Date fecha;
    private String hora;
    private String nombreOpcional;
    private String usuario;
}
