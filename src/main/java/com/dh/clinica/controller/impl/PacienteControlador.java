package com.dh.clinica.controller.impl;

import com.dh.clinica.controller.CRUDController;
import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.model.Domicilio;
import com.dh.clinica.model.Odontologo;
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
    public ResponseEntity<String> registrar(@RequestBody Paciente paciente) throws BadRequestException, ResourceNotFoundException {
        ResponseEntity<String> respuesta = null;
        if (pacienteServicio.registrar(paciente)!= null){
            respuesta = ResponseEntity.ok("Paciente registrado con éxito");
        if (paciente.getNombre().isEmpty() || paciente.getApellido().isEmpty() || paciente.getDni() == null || paciente.getFechaIngreso() == null || paciente.getDomicilio() == null)
            throw new BadRequestException("No deben haber campos vacíos");
        }else{
            respuesta = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se puedo registar el paciente");
        }
        return respuesta;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable Integer id) throws BadRequestException, ResourceNotFoundException{
        Paciente paciente = pacienteServicio.buscarPorId(id).orElse(null);
        return ResponseEntity.ok(paciente);
    }


    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizar(@RequestBody Paciente paciente)throws BadRequestException, ResourceNotFoundException {
        ResponseEntity<String> respuesta = null;

        if (paciente.getId() != null && pacienteServicio.buscarPorId(paciente.getId()).isPresent()){
            pacienteServicio.actualizar(paciente);
            respuesta = ResponseEntity.ok("Paciente actualizado");
        if (paciente.getNombre().isEmpty() || paciente.getApellido().isEmpty() || paciente.getDni() == null || paciente.getFechaIngreso() == null || paciente.getDomicilio() == null)
            throw new BadRequestException("No deben haber campos vacíos");
        } else {
            respuesta = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente no encontrado");
        }
        return respuesta;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) throws ResourceNotFoundException, BadRequestException {
        ResponseEntity<String> respuesta = null;

        if (pacienteServicio.buscarPorId(id).isPresent()) {
        pacienteServicio.eliminar(id);
        respuesta = ResponseEntity.ok("Se eliminó el odontólogo con id " + id);
        if (pacienteServicio.buscarPorId(id).isEmpty())
            respuesta = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error específicando el Id");
        } else {
        respuesta = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe paciente con el Id: " + id);
        }
        return respuesta;
        }

    @GetMapping("/todos")
    public ResponseEntity<List<Paciente>> buscarTodos(){
        return ResponseEntity.ok(pacienteServicio.buscarTodos());
    }
}
