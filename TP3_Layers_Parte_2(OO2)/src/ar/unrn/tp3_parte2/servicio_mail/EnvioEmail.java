package ar.unrn.tp3_parte2.servicio_mail;

import ar.unrn.tp3_parte2.libreria.Email;
import ar.unrn.tp3_parte2.libreria.SmtpException;
import ar.unrn.tp3_parte2.modelo.EnvioDeEmails;

public class EnvioEmail implements EnvioDeEmails {

	@Override
	public void enviarEmail(String destinatario, String titulo, String cuerpo) {
		Email e = new Email(destinatario, titulo, cuerpo);
		try {
			e.enviar();
		} catch (SmtpException e1) {
			throw new RuntimeException("No se pudo enviar el email", e1);
		}

	}

}
