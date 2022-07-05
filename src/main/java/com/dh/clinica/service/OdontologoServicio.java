package com.dh.clinica.service;

import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.GlobalExceptionsHandler;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.model.Odontologo;
import com.dh.clinica.repository.impl.OdontologoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoServicio extends GlobalExceptionsHandler {
    private static Logger log = Logger.getLogger(OdontologoServicio.class);

    private final OdontologoRepository odontologoRepository;

    @Autowired
    public OdontologoServicio(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }
    public String registrar(Odontologo odontologo) {
        if (odontologoRepository.save(odontologo) != null){ ;
        log.info("Dentista eliminado");
        return "Nuevo odontólogo guardado";
    }else{
        log.error("Algo malió sal");
        return "Algo malió sal";
        }
    }
    //public Odontologo registrar(Odontologo odontologo) throws BadRequestException{
    //    if(odontologo == null)
    //        throw  new BadRequestException("No pueden haber campos vacíos");
    //    return odontologoRepository.save(odontologo);
    //}

    public void eliminar(Integer id) throws ResourceNotFoundException, BadRequestException {
        if (id == null || id < 1)
        throw new BadRequestException("El id del odontólogo no puede ser null ni negativo");
        if (!odontologoRepository.existsById(id))
            throw new ResourceNotFoundException("No existe ningún odontólogo con id: " + id);
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
