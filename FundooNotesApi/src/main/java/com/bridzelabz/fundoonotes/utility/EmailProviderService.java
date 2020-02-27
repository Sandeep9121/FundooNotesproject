package com.bridzelabz.fundoonotes.utility;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

@Component
public class EmailProviderService {
	public static void sendMail(String emailContact, String emailSubject, String body) {

		String fromEmail = System.getenv("email");
		String password = System.getenv("password");
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");

		Authenticator auth = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};

		Session session = Session.getInstance(prop, auth);
		send(session, fromEmail, emailContact, emailSubject, body);
		// sendMail(emailContact, emailSubject, body);
		// send(session, fromEmail, emailContact, emailSubject, body);
	}// end of send mail

	private static void send(Session session, String fromEmail, String emailContact, String emailSubject, String body) {
		// TODO Auto-generated method stub
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromEmail, "Mr.Sandeep"));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress());
			message.setSubject(emailSubject);
			message.setText(body);
			Transport.send(message);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("exception occured while sending mail");

		}
	}

}
