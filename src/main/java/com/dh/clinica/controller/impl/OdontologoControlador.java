package com.dh.clinica.controller.impl;

import com.dh.clinica.controller.CRUDController;
import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.model.Odontologo;
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
    public ResponseEntity<String> registrar(@RequestBody Odontologo odontologo) throws BadRequestException{
        ResponseEntity<String> respuesta = null;
        if (odontologoServicio.registrar(odontologo)!= null){
            respuesta = ResponseEntity.ok("Odontólogo creado con éxito");
        if (odontologo.getNombre().isEmpty() || odontologo.getApellido().isEmpty() || odontologo.getMatricula() == null)
            throw new BadRequestException("No deben haber campos campos vacíos");
        }else{
            respuesta = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se puedo registar el odontólogo");
        }
        return respuesta;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarPorId(@PathVariable Integer id) throws BadRequestException, ResourceNotFoundException {
        Odontologo odontologo = odontologoServicio.buscarPorId(id).orElse(null);

        return ResponseEntity.ok(odontologo);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizar(@RequestBody Odontologo odontologo) throws BadRequestException, ResourceNotFoundException{
        ResponseEntity<String> respuesta = null;

        if (odontologo.getId() != null && odontologoServicio.buscarPorId(odontologo.getId()).isPresent()){
            odontologoServicio.actualizar(odontologo);
            respuesta = ResponseEntity.ok("Odontólogo actualizado");
        if (odontologo.getNombre().isEmpty() || odontologo.getApellido().isEmpty() || odontologo.getMatricula() == null)
           throw new BadRequestException("No deben haber campos campos vacíos");
        } else {
            respuesta = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Odontólogo no encontrado");
        }
        return respuesta;
        }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) throws BadRequestException, ResourceNotFoundException {
        ResponseEntity<String> respuesta = null;

        if (odontologoServicio.buscarPorId(id).isPresent()) {
            odontologoServicio.eliminar(id);
            respuesta = ResponseEntity.ok("Se eliminó el odontólogo con id " + id);
        if (odontologoServicio.buscarPorId(id).isEmpty())
            respuesta = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error específicando el Id");
        } else {
            respuesta = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe odontólogo con el Id: " + id);
        }
            return respuesta;
        }





    @GetMapping("/todos")
    public ResponseEntity<List<Odontologo>> buscarTodos() {
        return ResponseEntity.ok(odontologoServicio.buscarTodos());
    }



}
