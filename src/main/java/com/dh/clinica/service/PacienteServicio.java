package com.dh.clinica.service;


import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.GlobalExceptionsHandler;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.model.Paciente;
import com.dh.clinica.repository.impl.PacienteRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PacienteServicio extends GlobalExceptionsHandler {
    private static Logger log = Logger.getLogger(OdontologoServicio.class);

    private final PacienteRepository pacienteRepository;

    @Autowired
    public PacienteServicio(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public String registrar(Paciente paciente) throws BadRequestException, ResourceNotFoundException {
        if (pacienteRepository.save(paciente) != null)
            log.info("Paciente registrado con éxito");
        if (paciente == null)
            throw new BadRequestException("Algo salió mal, revise los campos");
        return "Algo malió sal";
    }

    public void eliminar(Integer id) throws ResourceNotFoundException, BadRequestException {
        if (id == null || id < 1)
            throw new BadRequestException("El id del odontólogo no puede estar vacío ni negativo");
        if (!pacienteRepository.existsById(id))
            throw new ResourceNotFoundException("No existe ningún odontólogo con id: " + id);
        pacienteRepository.deleteById(id);
    }

    public Optional<Paciente> buscarPorId(Integer id) throws ResourceNotFoundException, BadRequestException{
        if (id == null || id < 1)
            throw new BadRequestException("El id del paciente no puede estar vacío ni negativo");
        Paciente paciente = pacienteRepository.findById(id).orElse(null);
        if (paciente == null)
            throw new ResourceNotFoundException("El paciente no existe");
        return pacienteRepository.findById(id);
    }

    public List<Paciente> buscarTodos(){
        return pacienteRepository.findAll();
    }

    public Paciente actualizar(Paciente paciente)throws BadRequestException {
        if (paciente == null)
            throw new BadRequestException("No pueden haber campos vacíos");
        if (paciente.getId() == null)
            throw new BadRequestException("El id del odontólogo no puede estar vacío");

        return pacienteRepository.save(paciente);
    }
}
