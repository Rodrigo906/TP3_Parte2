package ar.unrn.tp3_parte2.modelo;

import java.util.List;

public interface RepositorioEmpleados {

	public void registrarEmpleado(Empleado empleado);

	public List<Empleado> obtenerEmpleadosCumplenHoy();

}
