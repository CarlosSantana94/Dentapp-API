package com.bucapps.dentapp.controllers;

import com.bucapps.dentapp.models.dto.ApartaCitaDto;
import com.bucapps.dentapp.models.entity.Cita;
import com.bucapps.dentapp.services.CitasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600)
public class CitasController {

    @Autowired
    private CitasService citasService;

    @GetMapping(value = "citas/{doctorId}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<Date>> obtenerDiasDisponiblesPorDr(@PathVariable(name = "doctorId") Long doctorId) {
        return ResponseEntity.ok(citasService.obtenerDiasDisponiblesPorDr(doctorId));
    }

    @GetMapping(value = "citas/{doctorId}/{fecha}/{huboCambio}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<Time>> obtenerDiasDisponiblesPorDr(
            @PathVariable(name = "doctorId") Long doctorId,
            @PathVariable(name = "fecha") String fecha,
            @PathVariable(name = "huboCambio") Boolean huboCambio,
            @RequestHeader(name = "idUsuario") String idUsuario
    ) throws ParseException {
        return ResponseEntity.ok(citasService.obtenerHorarioDisponiblePorDoctorYDia(doctorId, fecha, huboCambio, idUsuario));
    }

    @PostMapping(value = "citas", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Cita> apartarCita(@RequestBody ApartaCitaDto dto) {

        return ResponseEntity.ok(citasService.apartarCita(dto));

    }


    @PostMapping(value = "citas/confirmar/usuario", produces = "application/json")
    @ResponseBody
    public ResponseEntity<ApartaCitaDto> confirmarCitaUsuario(@RequestBody ApartaCitaDto dto) {

        return ResponseEntity.ok(citasService.confirmarCitaUsuario(dto));

    }

    @GetMapping(value = "citas/id/{citaId}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<ApartaCitaDto> obtenerCita(@PathVariable(name = "citaId") Long citaId) {
        return ResponseEntity.ok(citasService.obtenerCita(citaId));
    }

    @GetMapping(value = "citas/misCitas/", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<ApartaCitaDto>> misCitasUsuario(@RequestHeader(name = "idUsuario") String idUsuario) {
        return ResponseEntity.ok(citasService.misCitasUsuario(idUsuario));
    }

}
