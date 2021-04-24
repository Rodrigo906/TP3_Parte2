package ar.unrn.tp3_parte2.modelo;

import javax.management.RuntimeErrorException;

public class DireccionEmail {

	private String email;

	public DireccionEmail(String email) {
		if (validarEmail(email)) {
			this.email = email;
		} else
			throw new RuntimeErrorException(new Error(),
					"El email no tiene el formato correcto. Debe ser ingresado con el formato: example@gmail.com");

	}

	private boolean validarEmail(String email) {
		String formato = "[-\\w\\.]+@\\w+\\.\\w+";
		return email.matches(formato);
	}

	public String getValue() {
		return this.email;
	}

}
