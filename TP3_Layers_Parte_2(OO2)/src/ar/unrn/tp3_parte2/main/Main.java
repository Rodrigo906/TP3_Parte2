package ar.unrn.tp3_parte2.main;

import java.time.LocalDate;

import ar.unrn.tp3_parte2.modelo.Empleado;
import ar.unrn.tp3_parte2.modelo.GestorDeEnvioEmail;
import ar.unrn.tp3_parte2.modelo.RepositorioEmpleados;
import ar.unrn.tp3_parte2.persistencia.RegistroEnDisco;
import ar.unrn.tp3_parte2.servicio_mail.EnvioEmail;

public class Main {

	public static void main(String[] args) {

		LocalDate fechaActual = LocalDate.now();
		Empleado empleado1 = new Empleado("Rodrigo", "Calizaya", fechaActual, "Rodrigo@gmail.com");
		Empleado empleado2 = new Empleado("Pedro", "Ramirez", fechaActual.minusDays(1), "Pedro@gmail.com");
		Empleado empleado3 = new Empleado("Luis", "Lopez", fechaActual.plusDays(1), "Luis@gmail.com");

		RepositorioEmpleados repositorio = new RegistroEnDisco();

		repositorio.registrarEmpleado(empleado1);
		repositorio.registrarEmpleado(empleado2);
		repositorio.registrarEmpleado(empleado3);

		GestorDeEnvioEmail gestor = new GestorDeEnvioEmail(new EnvioEmail(), repositorio);
		gestor.enviarMailsDeFelizCumpleaños();

	}

}
