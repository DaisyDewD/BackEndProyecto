package com.dh.clinica.controller.impl;

import com.dh.clinica.controller.CRUDController;
import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.model.Paciente;
import com.dh.clinica.model.Turno;
import com.dh.clinica.service.OdontologoServicio;
import com.dh.clinica.service.PacienteServicio;
import com.dh.clinica.service.TurnoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoControlador implements CRUDController<Turno> {

    @Autowired
    private TurnoServicio turnoServicio;
    @Autowired
    private PacienteServicio pacienteServicio;
    @Autowired
    private OdontologoServicio odontologoServicio;

    @PostMapping("/nuevo")
    public ResponseEntity<String> registrar(@RequestBody Turno turno) throws ResourceNotFoundException, BadRequestException {
        ResponseEntity<String> respuesta = null;
        if(turnoServicio.registrar(turno) != null){
            respuesta = ResponseEntity.ok("El turno fue registrado con éxito");
        }else{
            respuesta = ResponseEntity.internalServerError().body("Ooops");
        }
        return respuesta;
    }

    //public ResponseEntity<Turno> registrar(@RequestBody Turno turno) throws ResourceNotFoundException, BadRequestException {
    //    ResponseEntity<Turno> response;
    //    if (pacienteServicio.buscarPorId(turno.getPaciente().getId()).isPresent() && odontologoServicio.buscarPorId(turno.getOdontologo().getId()).isPresent())
    //        response = ResponseEntity.ok(turnoServicio.registrar(turno));
//
    //    else
    //        response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//
    //    return response;
    //}

    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarPorId(@PathVariable Integer id) {
        Turno turno = turnoServicio.buscarPorId(id).orElse(null);
        return ResponseEntity.ok(turno);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Turno>> buscarTodos() {
        return ResponseEntity.ok(turnoServicio.buscarTodos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) throws ResourceNotFoundException {
        ResponseEntity<String> response;
        if (turnoServicio.buscarPorId(id).isPresent()) {
            turnoServicio.eliminar(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Turno> actualizar(@RequestBody Turno turno) {
        return ResponseEntity.ok(turnoServicio.actualizar(turno));
    }
}
