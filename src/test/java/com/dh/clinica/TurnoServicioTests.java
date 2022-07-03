package com.dh.clinica;

import com.dh.clinica.model.Domicilio;
import com.dh.clinica.model.Odontologo;
import com.dh.clinica.model.Paciente;
import com.dh.clinica.model.Turno;
import com.dh.clinica.service.OdontologoServicio;
import com.dh.clinica.service.PacienteService;
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

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class TurnoServicioTests {

    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoServicio odontologoServicio;
    @Autowired
    private TurnoServicio turnoServicio;

    public void cargarDataSet() {
        Domicilio domicilio = new Domicilio("Av Santa fe", "444", "CABA", "Buenos Aires");
        Paciente p = pacienteService.registrarPaciente(new Paciente("Santiago", "Paz", "88888888", new Date(), domicilio));
        this.odontologoServicio.registrarOdontologo(new Odontologo("Santiago", "Paz", 3455647));

    }
    @Test
    public void altaTurnoTest(){


        this.cargarDataSet();
        turnoServicio.registrarTurno(new Turno(pacienteService.buscar(1).get(), odontologoServicio.buscar(1).get(),new Date()));

        Assert.assertNotNull(turnoServicio.buscar(1));

    }
    @Test
    public void buscarTurnoTest(){
        Assert.assertNotNull(turnoServicio.buscar(1));
    }
    @Test
    public void eliminarTurnoTest(){
        turnoServicio.eliminar(1);
        Assert.assertFalse(turnoServicio.buscar(1).isPresent());
    }




}
