package com.dh.clinica;



import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.model.Domicilio;
import com.dh.clinica.model.Paciente;
import com.dh.clinica.service.PacienteServicio;
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
public class PacienteServiceTest {
    @Autowired
    private PacienteServicio pacienteServicio;

    public void cargarDataSet() throws BadRequestException, ResourceNotFoundException {
        Domicilio domicilio = new Domicilio("Av Santa fe", "444", "CABA", "Buenos Aires");
        this.pacienteServicio.registrar(new Paciente("Santiago", "Paz", "88888888", new Date(), domicilio));
        Domicilio domicilio1 = new Domicilio("Av Avellaneda", "333", "CABA", "Buenos Aires");
        pacienteServicio.registrar(new Paciente("Micaela", "Perez", "99999999", new Date(), domicilio1));
    }



    @Test
    public void agregarYBuscarPacienteTest() throws BadRequestException, ResourceNotFoundException {
        this.cargarDataSet();
        Domicilio domicilio = new Domicilio("Calle", "123", "Temperley", "Buenos Aires");
        this.pacienteServicio.registrar(new Paciente("Tomas", "Pereyra", "12345678", new Date(), domicilio));

        Assert.assertNotNull(pacienteServicio.buscarPorId(1));
    }



    @Test
    public void eliminarPacienteTest() throws BadRequestException, ResourceNotFoundException {
        pacienteServicio.eliminar(3);
        Assert.assertTrue(pacienteServicio.buscarPorId(3).isEmpty());

    }

    @Test
    public void traerTodos() {
        List<Paciente> pacientes = pacienteServicio.buscarTodos();
        Assert.assertTrue(!pacientes.isEmpty());
        Assert.assertTrue(pacientes.size() == 2);
        System.out.println(pacientes);
    }


}
