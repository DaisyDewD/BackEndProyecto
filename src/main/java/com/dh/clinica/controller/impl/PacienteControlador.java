package com.dh.clinica.controller.impl;

import com.dh.clinica.controller.CRUDController;
import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.model.Domicilio;
import com.dh.clinica.model.Paciente;
import com.dh.clinica.service.PacienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteControlador implements CRUDController<Paciente> {
    @Autowired
    private PacienteServicio pacienteServicio;

    @PostMapping("/nuevo")
    public ResponseEntity<Paciente> registrar(@RequestBody Paciente paciente) {

        return ResponseEntity.ok(pacienteServicio.registrar(paciente));

    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable Integer id) {
        Paciente paciente = pacienteServicio.buscarPorId(id).orElse(null);

        return ResponseEntity.ok(paciente);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizar(@RequestBody Paciente paciente)throws BadRequestException, ResourceNotFoundException {
        ResponseEntity<String> response = null;

        if (paciente.getId() != null && pacienteServicio.buscarPorId(paciente.getId()).isPresent()){
            pacienteServicio.actualizar(paciente);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Odontólogo no encontrado");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) throws ResourceNotFoundException {
        ResponseEntity<String> response = null;

        if (pacienteServicio.buscarPorId(id).isPresent()) {
            pacienteServicio.eliminar(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Paciente>> buscarTodos(){
        return ResponseEntity.ok(pacienteServicio.buscarTodos());
    }
}
