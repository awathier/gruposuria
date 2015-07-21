package br.com.gruposuria.util;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {
	
	public static void main(String[] args) {

		String mensagem = "Teste";
		String destinatario = "awathier@gmail.com";

		//Properties properties = configuraConexao();
		//Session session = criandoSessao(properties);
		enviarMensagem(mensagem, destinatario);
	}

	public static String enviarMensagem(String mensagem, String destinatario) {
		
		Properties properties = configuraConexao();
		Session session = criandoSessao(properties);

		String resultado = "ok";
		String remetente = "awathier@gmail.com";
		String assunto = "Grupo Suria - Inscrição em Curso";

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(remetente)); // Remetente

			// Destinatario(s)
			Address[] toUser = InternetAddress.parse(destinatario);
			// .parse("awathier@gmail.com, seucolega@hotmail.com, seuparente@yahoo.com.br");
			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject(assunto);// Assunto
			message.setText(mensagem);
			/** M�todo para enviar a mensagem criada */

			Transport.send(message);
			System.out.println("Feito!!!");

		} catch (MessagingException e) {
			resultado = "email_nok";
			throw new RuntimeException(e);
		}

		System.out.println(resultado);
		
		return resultado;
	}

	public static Session criandoSessao(Properties p) {

		Session session = Session.getInstance(p, new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("awathier@gmail.com", "Wathier02");
					}
				});
		session.setDebug(true);
		return session;
	}

	public static Properties configuraConexao() {

		Properties props = new Properties();
		/** Parametros de conexao com servidor Gmail */
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		
		return props;
	}

}
