package com.emailclassification.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SenderAuthentication extends Authenticator {

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return (new PasswordAuthentication(
				"aklc.contact@gmail.com",
				"xxxxxxxxxx"));
	}
}
