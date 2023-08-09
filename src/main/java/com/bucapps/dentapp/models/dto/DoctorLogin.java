package com.bucapps.dentapp.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorLogin {
    private String email;
    private String nombre;
    private String password;
    private Long id;
    private Boolean primerRegistro;
    private String mensaje;

}
