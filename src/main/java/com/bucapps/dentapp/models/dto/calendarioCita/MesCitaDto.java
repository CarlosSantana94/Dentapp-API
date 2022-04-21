package com.bucapps.dentapp.models.dto.calendarioCita;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MesCitaDto {
    private Long mes;
    private List<DiaCitaDto> dias;
}
