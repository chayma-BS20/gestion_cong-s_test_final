package tn.esprit.templatespring.Entities;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
@Configuration
@ConfigurationProperties(prefix = "spring.mail")
public class Mail {
	   private String host;
	    private int port;
	    private String username;
	    private String password;
	    private boolean smtpAuth;
	    private boolean smtpStarttlsEnable;

	
	 public String getHost() {
			return host;
		}


		public void setHost(String host) {
			this.host = host;
		}


		public int getPort() {
			return port;
		}


		public void setPort(int port) {
			this.port = port;
		}


		public String getUsername() {
			return username;
		}


		public void setUsername(String username) {
			this.username = username;
		}


		public String getPassword() {
			return password;
		}


		public void setPassword(String password) {
			this.password = password;
		}


		public boolean isSmtpAuth() {
			return smtpAuth;
		}


		public void setSmtpAuth(boolean smtpAuth) {
			this.smtpAuth = smtpAuth;
		}


		public boolean isSmtpStarttlsEnable() {
			return smtpStarttlsEnable;
		}


		public void setSmtpStarttlsEnable(boolean smtpStarttlsEnable) {
			this.smtpStarttlsEnable = smtpStarttlsEnable;
		}


	    public JavaMailSender javaMailSender() {
	        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	        mailSender.setHost("smtp.google.com");
	        mailSender.setPort(587);
	        mailSender.setUsername("chayma.bensaad@esprit.tn");
	        mailSender.setPassword("223JFT2127");

	        java.util.Properties props = mailSender.getJavaMailProperties();
	        props.put("mail.transport.protocol", "smtp");
	        props.put("mail.smtp.auth", "true"); // Assurez-vous que l'authentification SMTP est activée
	        props.put("mail.smtp.starttls.enable", "true"); // Assurez-vous que le démarrage TLS est activé
	        props.put("mail.debug", "true");
	        return mailSender;
	    }
	 
	 
	  @Autowired
	    private JavaMailSender javaMailSender;

public Mail() {super();}
}
