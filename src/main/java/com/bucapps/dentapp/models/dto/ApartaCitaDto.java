package com.bucapps.dentapp.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApartaCitaDto {
    private Long id;
    private Long doctorId;
    private Date fecha;
    private String hora;
    private String nombreOpcional;
    private String telOpcional;
    private String usuario;
    private String tokenCardConekta;
    private Double precioCita;
    private String nombreDoctor;
    private String nombreClinica;
    private String direccionClinica;
    private String fotoDoctor;
    private String telDoctor;
    private Long estadoCita;
    private String descripcionEstadoCita;

}
