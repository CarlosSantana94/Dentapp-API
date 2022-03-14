package com.bucapps.dentapp.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class EstadoConfirmacion extends BaseEntity {

    private Long estado;
    private String nombre;

}
