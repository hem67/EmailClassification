package com.emailclassification.mail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendMail {

	public void send(List<String> to, String sub, String body) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Authenticator p = new SenderAuthentication();
		Session session = Session.getDefaultInstance(props, p);

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("aklc.contact@gmail.com", "EC"));

			Address[] rcv = new InternetAddress[to.size()];
			int i = 0;
			for (String t : to) {
				rcv[i++] = new InternetAddress(t);
			}
			message.addRecipients(Message.RecipientType.TO, rcv);
			message.setSubject(sub);
			message.setContent(body, "text/html");

			Transport.send(message);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
