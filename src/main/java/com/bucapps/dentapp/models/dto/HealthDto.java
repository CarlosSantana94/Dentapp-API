package com.bucapps.dentapp.models.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class HealthDto {
    private Date fecha;
    private Boolean status;
}
