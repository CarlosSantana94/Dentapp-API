package com.bucapps.dentapp.models.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Direccion extends BaseEntity {

    private String calle;

    private String numero;

    private String interior;

    private String lat;

    private String lng;


}
