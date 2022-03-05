package com.bucapps.dentapp.controllers;

import com.bucapps.dentapp.models.dto.PromedioReseniaDto;
import com.bucapps.dentapp.models.entity.Resenias;
import com.bucapps.dentapp.services.ReseniasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600)
public class ReseniasController {

    @Autowired
    private ReseniasService reseniasService;

    @GetMapping(value = "resenia/promedio/{doctorId}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<PromedioReseniaDto> obtenerReseniaDelDr(@PathVariable(name = "doctorId") Long doctorId) {

        return ResponseEntity.ok(reseniasService.obtenerReseniaDelDr(doctorId));
    }

     @GetMapping(value = "resenia/todas/{doctorId}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<Resenias>> obtenerTodasLasReseniasPorDr(@PathVariable(name = "doctorId") Long doctorId) {

        return ResponseEntity.ok(reseniasService.obtenerTodasLasReseniasPorDr(doctorId));
    }


}
