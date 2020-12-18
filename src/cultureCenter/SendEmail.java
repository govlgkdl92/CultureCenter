package cultureCenter;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@SuppressWarnings("all")
public class SendEmail {

	private String email, content;

	public SendEmail(String email, String content) {
		this.email = email;
		this.content = content;
		
		Properties props = System.getProperties();
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.port", "465");
		props.put("mail.smtp.auth", "true");		
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.trust", true);
		
		String id = "dewkim6604@gmail.com";
		String pass = "happy9247";
		
		Session session = Session.getDefaultInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(id, pass);
			}
		});
		
		//메세지
		Message msg = new MimeMessage(session);
		
		//
		try {
			msg.setSubject("비트 문화센터의 인증번호입니다.");
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
			msg.setText("비트 문화 센터를 이용해주셔서 감사합니다.");
			msg.setText("인증번호 : "+content);
			msg.setSentDate(new Date());
			Transport.send(msg);
			System.out.println("메일 전송 완료");
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
	}
}
