package com.dh.clinica.service;

import com.dh.clinica.exceptions.GlobalExceptionsHandler;
import com.dh.clinica.model.Turno;
import com.dh.clinica.repository.impl.TurnoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoServicio extends GlobalExceptionsHandler {
    private static Logger log = Logger.getLogger(OdontologoServicio.class);

    private final TurnoRepository turnoRepository;

    @Autowired
    public TurnoServicio(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    public Turno registrar(Turno turno) {
        return turnoRepository.save(turno);
    }

    public List<Turno> buscarTodos() {
        return turnoRepository.findAll();
    }

    public void eliminar(Integer id) {
        turnoRepository.deleteById(id);
    }

    public Turno actualizar(Turno turno) {
        return turnoRepository.save(turno);
    }

    public Optional<Turno> buscarPorId(Integer id) {
        return turnoRepository.findById(id);
    }

}
