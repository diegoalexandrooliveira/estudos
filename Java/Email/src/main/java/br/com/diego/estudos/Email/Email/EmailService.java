package br.com.diego.estudos.Email.Email;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	public void enviarEmail(String nome, String destinatario) {

		try {
			Email email = new SimpleEmail();
			email.setHostName("smtp.gmail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("email", "senha"));
			email.setSSLOnConnect(true);
			email.setFrom("ddd@gmail.com");
			email.setSubject("Você foi convidado pela ListaVIP");
			email.setMsg("Olá " + nome + ". Você acaba de ser convidado pelo ListaVIP.");
			email.addTo(destinatario);
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}

	}

}
