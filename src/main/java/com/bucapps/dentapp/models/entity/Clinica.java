package com.bucapps.dentapp.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Clinica extends BaseEntity {

    private String nombre;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "direccion_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Direccion direccion;


}
