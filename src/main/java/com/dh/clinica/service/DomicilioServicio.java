package com.dh.clinica.service;


import com.dh.clinica.exceptions.GlobalExceptionsHandler;
import com.dh.clinica.model.Domicilio;
import com.dh.clinica.repository.impl.DomicilioRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DomicilioServicio extends GlobalExceptionsHandler {
    private static Logger log = Logger.getLogger(OdontologoServicio.class);
    private final DomicilioRepository domicilioRepository;

    @Autowired
    public DomicilioServicio(DomicilioRepository domicilioRepository) {
        this.domicilioRepository = domicilioRepository;
    }

    public Domicilio registrarDomicilio(Domicilio d){
        domicilioRepository.save(d);
        return d;
    }
    public Optional<Domicilio> buscar(Integer id){
        return Optional.of(domicilioRepository.getOne(Integer.valueOf(id)));
    }
    public List<Domicilio> buscarTodos(){
        return domicilioRepository.findAll();
    }
    public void eliminar(Integer id){
        domicilioRepository.deleteById(Integer.valueOf(id));
    }


}
