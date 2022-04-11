package com.bucapps.dentapp.controllers;

import com.bucapps.dentapp.models.dto.HealthDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@CrossOrigin(maxAge = 3600)
public class HealthController {

    @GetMapping(value = "", produces = "application/json")
    @ResponseBody
    public ResponseEntity<HealthDto> getHealth() {

        HealthDto healthDto = new HealthDto();
        healthDto.setFecha(new Date());
        healthDto.setStatus(true);


        return ResponseEntity.ok(healthDto);
    }

}
