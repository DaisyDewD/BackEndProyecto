package com.dh.clinica.controller;

import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;

public interface CRUDController<T> {



    ResponseEntity<?> registrar(T t)throws BadRequestException, ResourceNotFoundException;
    ResponseEntity<?> buscarPorId(Integer id) throws BadRequestException, ResourceNotFoundException;
    ResponseEntity<?> actualizar(T t) throws BadRequestException, ResourceNotFoundException;
    ResponseEntity<?> eliminar(Integer id)throws BadRequestException, ResourceNotFoundException;
    ResponseEntity<?> buscarTodos();



}