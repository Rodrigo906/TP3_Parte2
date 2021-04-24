package ar.unrn.tp3_parte2.test;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import ar.unrn.tp3_parte2.modelo.Empleado;
import ar.unrn.tp3_parte2.modelo.GestorDeEnvioEmail;
import ar.unrn.tp3_parte2.modelo.RepositorioEmpleados;
import ar.unrn.tp3_parte2.persistencia.RegistroEnMemoria;
import ar.unrn.tp3_parte2.servicio_mail.EnvioEmail;

public class TestEmpleado {

	@Test
	public void enviarMails() {

		LocalDate fechaActual = LocalDate.now();

		Empleado empleado1 = new Empleado("Rodrigo", "Calizaya", fechaActual, "Rodrigo@gmail.com");
		Empleado empleado2 = new Empleado("Pedro", "Ramirez", fechaActual.minusDays(1), "Pedro@gmail.com");
		Empleado empleado3 = new Empleado("Luis", "Lopez", fechaActual.plusDays(1), "Luis@gmail.com");

		RepositorioEmpleados repositorio = new RegistroEnMemoria();
		repositorio.registrarEmpleado(empleado1);
		repositorio.registrarEmpleado(empleado2);
		repositorio.registrarEmpleado(empleado3);

		GestorDeEnvioEmail gestor = new GestorDeEnvioEmail(new EnvioEmail(), repositorio);

		// si se enviaron los mails correctamente
		Assert.assertTrue(gestor.enviarMailsDeFelizCumpleaños());
		// Con los datos ingresados el resultado deberia ser solo el empleado de nombre
		// "Rodrigo"
		Assert.assertEquals("Rodrigo", repositorio.obtenerEmpleadosCumplenHoy().get(0).getNombre());

	}
}
