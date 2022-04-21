package com.bucapps.dentapp.models.dto.calendarioCita;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CitaDrDTO {
    private Long id;
    private String nombrePaciente;
    private String telPaciente;
    private String photoUrl;
    private Date fecha;
    private Time hora;
    private Long status;
    private String statusDescripcion;
}
