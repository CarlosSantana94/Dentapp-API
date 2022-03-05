package com.bucapps.dentapp.models.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Doctor extends BaseEntity {

    private String nombre;

    private Double tarifa;

    private String especialidad;

    private String photo;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinica_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Clinica clinica;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "disponibilidad_semanal_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private DisponibilidadSemanal disponibilidadSemanal;




}
