package com.bucapps.dentapp.models.dto.calendarioCita;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DiaCitaDto {
    private Date dia;
    private List<CitaDrDTO> citas;
}
