package com.dh.clinica.controller.impl;

import com.dh.clinica.controller.CRUDController;
import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.model.Odontologo;
import com.dh.clinica.model.Paciente;
import com.dh.clinica.service.OdontologoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/odontologos")
public class OdontologoControlador implements CRUDController<Odontologo> {
    @Autowired
    private OdontologoServicio odontologoServicio;

    @PostMapping("/nuevo")
    public ResponseEntity<Odontologo> registrar(@RequestBody Odontologo odontologo)throws BadRequestException, ResourceNotFoundException {

        Odontologo odontologoRegistrado = odontologoServicio.registrar(odontologo);
        return ResponseEntity.ok(odontologoRegistrado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarPorId(@PathVariable Integer id) throws BadRequestException, ResourceNotFoundException {
        Odontologo odontologo = odontologoServicio.buscarPorId(id).orElse(null);

        return ResponseEntity.ok(odontologo);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizar(@RequestBody Odontologo odontologo) throws BadRequestException, ResourceNotFoundException{
        ResponseEntity<String> response = null;

        if (odontologo.getId() != null && odontologoServicio.buscarPorId(odontologo.getId()).isPresent()){
            odontologoServicio.actualizar(odontologo);
        response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Odontólogo no encontrado");
    } else {
        response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) throws BadRequestException, ResourceNotFoundException {
        ResponseEntity<String> response = null;

        if (odontologoServicio.buscarPorId(id).isPresent()) {
            odontologoServicio.eliminar(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }




    @GetMapping("/todos")
    public ResponseEntity<List<Odontologo>> buscarTodos() {
        return ResponseEntity.ok(odontologoServicio.buscarTodos());
    }



}
