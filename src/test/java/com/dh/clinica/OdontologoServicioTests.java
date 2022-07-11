package com.dh.clinica;

import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.model.Odontologo;
import com.dh.clinica.service.OdontologoServicio;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class OdontologoServicioTests {
    @Autowired
    private OdontologoServicio odontologoServicio;


    public void cargarDataSet() throws BadRequestException {
        this.odontologoServicio.registrar(new Odontologo("Santiago", "Paz", 3455647));
    }

    @Test
    @Order(1)
    public void registrarOdontologo() throws  BadRequestException {
        this.cargarDataSet();
        String odontologo = String.valueOf(odontologoServicio.registrar(new Odontologo("Juan", "Ramirez", 348971960)));
        Assert.assertTrue(odontologo != null);

    }

    @Test
    @Order(3)
    public void eliminarOdontologoTest() throws ResourceNotFoundException, BadRequestException {

        odontologoServicio.eliminar(1);
       Assert.assertTrue(odontologoServicio.buscarPorId(1).isEmpty());

    }


    @Test
    @Order(2)
    public void traerTodosLosOdontologos(){
        List<Odontologo> odontologos = odontologoServicio.buscarTodos();
        Assert.assertTrue(!odontologos.isEmpty());
        Assert.assertTrue(odontologos.size() >= 1);
        System.out.println(odontologos);
    }

}
