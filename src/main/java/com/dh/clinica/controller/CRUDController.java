package com.dh.clinica.controller;

import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.model.Odontologo;
import com.dh.clinica.model.Paciente;
import org.springframework.http.ResponseEntity;

public interface CRUDController<T> {



    ResponseEntity<?> registrar(T t)throws BadRequestException, ResourceNotFoundException;
    ResponseEntity<?> buscarPorId(Integer id) throws BadRequestException, ResourceNotFoundException;
    ResponseEntity<String> actualizar(T t) throws BadRequestException, ResourceNotFoundException;
    ResponseEntity<String> eliminar(Integer id)throws BadRequestException, ResourceNotFoundException;
    ResponseEntity<?> buscarTodos();



}