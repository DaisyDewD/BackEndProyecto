package com.dh.clinica.controller.impl;

import com.dh.clinica.model.Domicilio;
import com.dh.clinica.service.DomicilioServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping()
public class DomicilioControlador {


    private DomicilioServicio domicilioServicio;

    @PostMapping()
    public ResponseEntity<Domicilio> registrar(@RequestBody Domicilio domicilio) {
        return ResponseEntity.ok(domicilioServicio.registrarDomicilio(domicilio));
    }
}
