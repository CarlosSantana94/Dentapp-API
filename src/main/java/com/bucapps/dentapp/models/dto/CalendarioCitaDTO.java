package com.bucapps.dentapp.models.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Getter
@Setter
public class CalendarioCitaDTO {
    private String title;
    private Date startTime;
    private Date endTime;
    private Boolean allDay;

    private Time tiempo;
    private java.sql.Date dia;
}
