package com.dh.clinica.controller;

import com.dh.clinica.model.Domicilio;
import com.dh.clinica.model.Odontologo;
import com.dh.clinica.service.DomicilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/domicilios")
public class DomicilioController {
    @Autowired
    private DomicilioService domicilioService;

    @PostMapping("/new")
    public ResponseEntity<Domicilio> registrarDomicilio(@RequestBody Domicilio domicilio) {

        return ResponseEntity.ok(domicilioService.guardar(domicilio));

    }

    @GetMapping("/todos")
    public ResponseEntity<List<Domicilio>> buscarTodos(){
        return ResponseEntity.ok(domicilioService.buscarTodos());
    }



}
