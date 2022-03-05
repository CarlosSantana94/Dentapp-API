package com.bucapps.dentapp.controllers;

import com.bucapps.dentapp.models.entity.Clinica;
import com.bucapps.dentapp.models.entity.Doctor;
import com.bucapps.dentapp.services.ClinicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600)
public class ClinicaController {

    @Autowired
    private ClinicaService clinicaService;

    @GetMapping(value = "clinica/{id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Clinica> obtenerDoctorPorId(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(clinicaService.obtenerClinicaPorId(id));
    }

    @GetMapping(value = "clinica/{id}/doctores", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<Doctor>> obtenerDoctoresPorClinica(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(clinicaService.obtenerDoctoresPorClinica(id));
    }


    @GetMapping(value = "clinica", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<Clinica>> obtenerClinicas() {
        return ResponseEntity.ok(clinicaService.obtenerClinicas());
    }

}
