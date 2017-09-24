package com.foodkonnekt.util;

import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class SendMailProperty {

    public static Session mailProperty(final String fromEmail, final String password) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "mko.mkonnekt.com");
        props.put("mail.smtp.port", "25");
        props.put("mail.smtp.ssl.trust", "mko.mkonnekt.com");
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });
        return session;
    }
}
