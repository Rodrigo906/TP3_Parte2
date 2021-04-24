package ar.unrn.tp3_parte2.persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ar.unrn.tp3_parte2.modelo.Empleado;
import ar.unrn.tp3_parte2.modelo.RepositorioEmpleados;

public class RegistroEnDisco implements RepositorioEmpleados {

	@Override
	public void registrarEmpleado(Empleado empleado) {
		try {
			String registro = empleado.toString() + "\r";
			Files.write(Paths.get("C:/Users/Rodrigo/archivoPruebaLayers2.txt"), registro.getBytes(),
					StandardOpenOption.APPEND);
		} catch (IOException e) {
			throw new RuntimeException("No se pudo persistir", e);
		}

	}

	@Override
	public List<Empleado> obtenerEmpleadosCumplenHoy() {
		List<Empleado> empleados = new ArrayList<Empleado>();
		String unRegistro = null;
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		String[] parts;

		archivo = new File("C:/Users/Rodrigo/archivoPruebaLayers2.txt");
		try {
			fr = new FileReader(archivo);

			br = new BufferedReader(fr);
			while ((unRegistro = br.readLine()) != null) {
				parts = unRegistro.split(",");
				if (CumpleAñosHoy(parts[2])) {
					empleados.add(reconstruirEmpleado(unRegistro));
				}
			}

			br.close();

		} catch (FileNotFoundException e) {
			throw new RuntimeException("Archivo no encontrado", e);
		} catch (IOException e) {
			throw new RuntimeException("No se pudo leer el archivo", e);
		}
		return empleados;

	}

	private boolean CumpleAñosHoy(String fecha) {
		LocalDate fechaActual = LocalDate.now();
		String fechaActualSinAño = rellenarConCeroSiEsNecesario(fechaActual.getMonthValue()) + "-"
				+ rellenarConCeroSiEsNecesario(fechaActual.getDayOfMonth());
		String fechaIngresada = fecha.substring(5, 10);
		if (fechaActualSinAño.equals(fechaIngresada)) {
			return true;
		}
		return false;
	}

	private Empleado reconstruirEmpleado(String lineaRegistro) {
		String[] parts = lineaRegistro.split(",");
		Empleado empleado = null;
		empleado = new Empleado(parts[0], parts[1], construirFecha(parts[2]), parts[3]);
		return empleado;
	}

	private LocalDate construirFecha(String fecha) {
		String[] parts = fecha.split("-");
		LocalDate fechaReconstruida = LocalDate.of(Integer.valueOf(parts[0]), Integer.valueOf(parts[1]),
				Integer.valueOf(parts[2]));
		return fechaReconstruida;
	}

	private String rellenarConCeroSiEsNecesario(int fecha) {
		String corregido = String.valueOf(fecha);
		if (fecha <= 9) {
			corregido = "0" + fecha;
		}
		return corregido;
	}

}
