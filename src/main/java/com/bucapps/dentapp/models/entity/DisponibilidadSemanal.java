package com.bucapps.dentapp.models.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.sql.Time;

@Getter
@Setter
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DisponibilidadSemanal extends BaseEntity{

    private Boolean lunes;
    private Time lunesApertura;
    private Time lunesCierre;

    private Boolean martes;
    private Time martesApertura;
    private Time martesCierre;

    private Boolean miercoles;
    private Time miercolesApertura;
    private Time miercolesCierre;

    private Boolean jueves;
    private Time juevesApertura;
    private Time juevesCierre;

    private Boolean viernes;
    private Time viernesApertura;
    private Time viernesCierre;

    private Boolean sabado;
    private Time sabadoApertura;
    private Time sabadoCierre;

    private Boolean domingo;
    private Time domingoApertura;
    private Time domingoCierre;


}
