package com.bucapps.dentapp.controllers;

import com.bucapps.dentapp.models.entity.Doctor;
import com.bucapps.dentapp.services.DoctoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600)
public class DoctoresController {

    @Autowired
    private DoctoresService doctoresService;


    @GetMapping(value = "doctores", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<Doctor>> obtenerTodosLosDoctores() {

        return ResponseEntity.ok(doctoresService.obtenerTodosLosDoctores());
    }

    @GetMapping(value = "doctores/{id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Doctor> obtenerDoctorPorId(@PathVariable(name = "id") Long id) {

        return ResponseEntity.ok(doctoresService.obtenerDoctorPorId(id));
    }

}
