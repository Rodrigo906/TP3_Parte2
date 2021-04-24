package ar.unrn.tp3_parte2.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.unrn.tp3_parte2.modelo.Empleado;
import ar.unrn.tp3_parte2.modelo.RepositorioEmpleados;

public class RegistroEnBD implements RepositorioEmpleados {

	private Connection dbConn;

	@Override
	public void registrarEmpleado(Empleado empleado) {
		setupBaseDeDatos();
		PreparedStatement st = null;
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date fecha = Date.from(empleado.getFechaNacimiento().atStartOfDay(defaultZoneId).toInstant());
		Timestamp tfecha = new Timestamp(fecha.getTime());
		try {
			st = dbConn.prepareStatement(
					"insert into empleado (nombre, apellido, fecha_nacimiento, direccion_email) values(?,?,?,?)");
			st.setString(1, empleado.getNombre());
			st.setString(2, empleado.getApellido());
			st.setTimestamp(3, tfecha);
			st.setString(4, empleado.getEmail().getValue());
			st.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("Error al persistir", e);
		} finally {
			try {
				st.close();
			} catch (SQLException e) {
				throw new RuntimeException("Error al persistir", e);
			}
		}

	}

	@Override
	public List<Empleado> obtenerEmpleadosCumplenHoy() {
		setupBaseDeDatos();
		PreparedStatement st = null;
		List<Empleado> empleados = new ArrayList<Empleado>();
		Empleado empleado = null;
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Instant instant = null;
		LocalDate fechaActual = LocalDate.now();
		String mes_Dia = "%" + fechaActual.getMonthValue() + "-" + fechaActual.getDayOfMonth() + "%";
		Date fechaExtraida = null;
		try {
			st = dbConn.prepareStatement("SELECT * FROM empleado WHERE fecha_nacimiento LIKE ?");
			st.setString(1, mes_Dia);

			ResultSet resulS = st.executeQuery();
			while (resulS.next()) {

				fechaExtraida = new java.util.Date(resulS.getTimestamp("fecha_nacimiento").getTime());
				instant = fechaExtraida.toInstant();
				fechaActual = instant.atZone(defaultZoneId).toLocalDate();
				empleado = new Empleado(resulS.getString("nombre"), resulS.getString("apellido"), fechaActual,
						resulS.getString("direccion_email"));
				empleados.add(empleado);
			}

			resulS.close();
			st.close();
			dbConn.close();
		} catch (SQLException e) {
			throw new RuntimeException("Error al obtener empleados cumpleañeros", e);
		}
		return empleados;
	}

	private void setupBaseDeDatos() {
		String url = "jdbc:mysql://localhost:3306/tp3_layers";
		String user = "root";
		String password = "";
		try {
			this.dbConn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new RuntimeException("Error al persistir", e);
		}

	}

}
