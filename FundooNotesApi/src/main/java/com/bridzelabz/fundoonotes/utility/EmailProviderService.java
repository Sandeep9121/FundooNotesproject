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
public class EmailProviderService
/*
 * { private String from; private String to; private String subject; private
 * String text; public EmailProviderService (String from, String to, String
 * subject, String text){ this.from = from; this.to = to; this.subject =
 * subject; this.text = text; } public void send(){
 * 
 * Properties props = new Properties(); props.put("mail.smtp.host",
 * "smtp.gmail.com"); props.put("mail.smtp.port", "465");
 * 
 * Session mailSession = Session.getDefaultInstance(props); Message
 * simpleMessage = new MimeMessage(mailSession);
 * 
 * InternetAddress fromAddress = null; InternetAddress toAddress = null; try {
 * fromAddress = new InternetAddress(from); toAddress = new InternetAddress(to);
 * } catch (AddressException e) { e.printStackTrace(); }
 * 
 * try { simpleMessage.setFrom(fromAddress);
 * simpleMessage.setRecipient(RecipientType.TO, toAddress);
 * simpleMessage.setSubject(subject); simpleMessage.setText(text);
 * Transport.send(simpleMessage); } catch (MessagingException e) {
 * e.printStackTrace(); } } }
 */

{
	public static void sendMail(String emailContact, String emailSubject, String body) {

		String fromEmail ="sandeep.rayala14@gmail.com" ;    //System.getenv("email");
		String password ="S114D5516@sanju1997";        //System.getenv("password");
		Properties property = new Properties();
		property.put("mail.smtp.auth", "true");
		property.put("mail.smtp.starttls.enable", "true");
		property.put("mail.smtp.host", "smtp.gmail.com");
		property.put("mail.smtp.port", "587");

		Authenticator auth = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};

	
		Session session = Session.getInstance(property, auth);

		send(session, fromEmail, emailContact, emailSubject, body);

		// sendMail(emailContact, emailSubject, body);
		// send(session, fromEmail, emailContact, emailSubject, body);
	}// end of send mail

	private static void send(Session session, String fromEmail, String emailContact, String emailSubject, String body) {
		// TODO Auto-generated method stub
		try {

			MimeMessage mimeMessage = new MimeMessage(session);

		/*	mimeMessage.addHeader("Content-type", "text/HTML; charset=UTF-8");

			mimeMessage.addHeader("format", "flowed");

			mimeMessage.addHeader("Content-Transfer-Encoding", "8bit");*/

			mimeMessage.setFrom(new InternetAddress(fromEmail, "Mr.Sandeep"));

			mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(emailContact));

			//mimeMessage.setReplyTo(InternetAddress.parse(" u cannosandeep.rayala14@gmial.com", false));

			mimeMessage.setSubject(emailSubject);

			mimeMessage.setText(body);

			Transport.send(mimeMessage);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("exception occured while sending mail");

		}
	}

}
