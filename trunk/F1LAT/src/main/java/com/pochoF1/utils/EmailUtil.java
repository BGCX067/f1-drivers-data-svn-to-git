package com.pochoF1.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailUtil {

	private static Logger log = LoggerFactory.getLogger(EmailUtil.class);

	public static void enviarMail(String emailContacto, String mensaje, String nombreContacto) {

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("pochof1.datos@gmail.com", "noseqponer24");
			}
		});

		try {

			String mensajeFinal = "Ariel," + "\n\nTe contactaron desde pochof1.com.ar" + "\n\n Nombre: " + nombreContacto + "\nMail: " + emailContacto + "\nMensaje: " + mensaje;

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("pochof1.datos@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("aribesomi@gmail.com"));
			message.setSubject("Contacto desde pochof1.com.ar");
			message.setText(mensajeFinal);

			Transport.send(message);

		} catch (MessagingException e) {
			log.error(e.getMessage(), e);
		}

	}

}
