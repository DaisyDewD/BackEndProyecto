package com.dh.clinica.controller.impl;

import com.dh.clinica.controller.CRUDController;
import com.dh.clinica.model.Domicilio;
import com.dh.clinica.service.DomicilioServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/domicilios")
public class DomicilioControlador {


    private DomicilioServicio domicilioServicio;

    @PostMapping("/nuevo")
    public ResponseEntity<Domicilio> registrar(@RequestBody Domicilio domicilio) {
        return ResponseEntity.ok(domicilioServicio.registrarDomicilio(domicilio));
    }



    @GetMapping("/todos")
    public ResponseEntity<List<Domicilio>> buscarTodos(){
        return ResponseEntity.ok(domicilioServicio.buscarTodos());
    }



}
