package com.bucapps.dentapp.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Usuario extends BaseEntity {
    private String nombre;
    private String email;
    private String token;
    private String usuario;
    private String imageUrl;
    private String gcm;
}
