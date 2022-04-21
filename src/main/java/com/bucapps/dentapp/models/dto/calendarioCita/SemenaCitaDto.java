package com.bucapps.dentapp.models.dto.calendarioCita;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SemenaCitaDto {
    private Long semana;
    private Date desde;
    private Date hasta;
    private List<DiaCitaDto> dias;
}
