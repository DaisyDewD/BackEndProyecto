package com.dh.clinica.controller;

import com.dh.clinica.model.Domicilio;
import com.dh.clinica.service.DomicilioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/domicilios")
public class DomicilioControlador {
    @Autowired
    private DomicilioServicio domicilioServicio;

    @PostMapping("/nuevo")
    public ResponseEntity<Domicilio> registrarDomicilio(@RequestBody Domicilio domicilio) {

        return ResponseEntity.ok(domicilioServicio.registrarDomicilio(domicilio));

    }

    @GetMapping("/todos")
    public ResponseEntity<List<Domicilio>> buscarTodos(){
        return ResponseEntity.ok(domicilioServicio.buscarTodos());
    }



}
