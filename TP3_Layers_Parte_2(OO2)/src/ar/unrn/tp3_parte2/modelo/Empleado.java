package ar.unrn.tp3_parte2.modelo;

import java.time.LocalDate;

public class Empleado {

	private String nombre;
	private String apellido;
	private LocalDate fechaNacimiento;
	private DireccionEmail email;

	public Empleado(String nombre, String apellido, LocalDate fechaNacimiento, String email) {

		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.email = new DireccionEmail(email);
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public DireccionEmail getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return nombre + "," + apellido + "," + fechaNacimiento + "," + email.getValue();
	}

}
