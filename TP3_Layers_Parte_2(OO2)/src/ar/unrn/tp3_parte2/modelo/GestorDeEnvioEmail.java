package ar.unrn.tp3_parte2.modelo;

public class GestorDeEnvioEmail {

	private EnvioDeEmails envioEmails;
	private RepositorioEmpleados repositorio;

	public GestorDeEnvioEmail(EnvioDeEmails implementacionEnvio, RepositorioEmpleados implementacionPersistencia) {
		this.envioEmails = implementacionEnvio;
		this.repositorio = implementacionPersistencia;
	}

	// cambiar
	public boolean enviarMailsDeFelizCumpleaños() {
		boolean envioExitoso = false;
		for (Empleado emp : repositorio.obtenerEmpleadosCumplenHoy()) {
			envioEmails.enviarEmail(emp.getEmail().getValue(), "Cumpleaños", "Feliz Cumpleaños");
		}
		envioExitoso = true;
		return envioExitoso;
	}

}
