package com.dh.clinica;

import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.model.Domicilio;
import com.dh.clinica.model.Odontologo;
import com.dh.clinica.model.Paciente;
import com.dh.clinica.model.Turno;
import com.dh.clinica.service.OdontologoServicio;
import com.dh.clinica.service.PacienteServicio;
import com.dh.clinica.service.TurnoServicio;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;



@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class TurnoServicioTests {

    @Autowired
    private PacienteServicio pacienteServicio;
    @Autowired
    private OdontologoServicio odontologoServicio;
    @Autowired
    private TurnoServicio turnoServicio;


    public void cargarDataSet() throws ResourceNotFoundException, BadRequestException {
        Domicilio domicilio = new Domicilio("Av Santa fe", "444", "CABA", "Buenos Aires");
        Paciente p = pacienteServicio.registrar(new Paciente("Santiago", "Paz", "88888888", new Date(), domicilio));
        this.odontologoServicio.registrar(new Odontologo("Santiago", "Paz", 3455647));

    }
    @Test
    public void RegistrarTurnoTest() throws ResourceNotFoundException, BadRequestException {


        this.cargarDataSet();
        turnoServicio.registrar(new Turno(pacienteServicio.buscarPorId(1).get(), odontologoServicio.buscarPorId(1).get(),new Date()));

        Assert.assertNotNull(turnoServicio.buscar(1));

    }

    //@Test
    //public void ActualizarTurnoTest()throws ResourceNotFoundException, BadRequestException {
    //   turnoServicio.actualizar(1).get(),new Date();
    //    Assert.assertNotNull(turnoServicio.buscar(1));
    //}

    @Test
    public void buscarTurnoTest(){
        Assert.assertNotNull(turnoServicio.buscar(1));
    }
    @Test
    public void eliminarTurnoTest(){
        turnoServicio.eliminar(1);
        Assert.assertFalse(turnoServicio.buscar(1).isPresent());
    }

    @Test
    public void traerTodosLosTurnosTest(){
            List<Turno> turnos = turnoServicio.listar();
            Assert.assertTrue(!turnos.isEmpty());
            Assert.assertTrue(turnos.size() == 1);
            System.out.println(turnos);
        }




}
