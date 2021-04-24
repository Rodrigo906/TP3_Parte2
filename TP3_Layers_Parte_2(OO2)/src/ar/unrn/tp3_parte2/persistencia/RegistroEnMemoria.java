package ar.unrn.tp3_parte2.persistencia;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ar.unrn.tp3_parte2.modelo.Empleado;
import ar.unrn.tp3_parte2.modelo.RepositorioEmpleados;

public class RegistroEnMemoria implements RepositorioEmpleados {

	List<Empleado> empleados = new ArrayList<Empleado>();

	@Override
	public void registrarEmpleado(Empleado empleado) {
		empleados.add(empleado);
	}

	@Override
	public List<Empleado> obtenerEmpleadosCumplenHoy() {
		List<Empleado> empCumpleañeros = new ArrayList<Empleado>();
		LocalDate fechaActual = LocalDate.now();
		String mes_diaActual = fechaActual.getMonthValue() + "-" + fechaActual.getDayOfMonth();
		String mes_diaCumpEmp = null;

		for (Empleado e : this.empleados) {
			mes_diaCumpEmp = e.getFechaNacimiento().getMonthValue() + "-" + e.getFechaNacimiento().getDayOfMonth();
			if (mes_diaActual.equals(mes_diaCumpEmp)) {
				empCumpleañeros.add(e);
			}
		}
		return empCumpleañeros;
	}

}
