package com.dh.clinica.service;

import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.model.Odontologo;
import com.dh.clinica.repository.impl.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoServicio {

    private final OdontologoRepository odontologoRepository;

    @Autowired
    public OdontologoServicio(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    public Odontologo registrar(Odontologo odontologo) throws BadRequestException{
        if(odontologo == null)
            throw  new BadRequestException("No pueden haber campos vacíos");
        return odontologoRepository.save(odontologo);

    }

    public void eliminar(Integer id) throws ResourceNotFoundException, BadRequestException {
        if(buscarPorId(id).isEmpty())
            throw  new ResourceNotFoundException("No existe odontólogo con id: "+ id);
        odontologoRepository.deleteById(id);
    }

    public Optional<Odontologo> buscarPorId(Integer id) throws ResourceNotFoundException, BadRequestException{
        return odontologoRepository.findById(id);
    }

    public List<Odontologo> buscarTodos(){
        return odontologoRepository.findAll();
    }

    public Odontologo actualizar(Odontologo odontologo)throws BadRequestException {
        if (odontologo == null)
            throw new BadRequestException("No pueden haber campos vacíos");
        if (odontologo.getId() == null)
            throw new BadRequestException("El id del odontólogo no puede estar vacío");

        return odontologoRepository.save(odontologo);
    }
}
