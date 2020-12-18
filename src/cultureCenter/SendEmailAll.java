package cultureCenter;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import member.bean.MemberDTO;

@SuppressWarnings("all")
public class SendEmailAll {

	private String email, content;
	private List<MemberDTO> dtoList;

	public SendEmailAll(List<MemberDTO> dtoList, String content) {	
		
		this.dtoList = dtoList;
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
	
		for(int i = 0; i<dtoList.size(); i++) {
			
			try {
				msg.setSubject("(광고) *** 이번 달 비트 문화센터  강의 정보 *** ");
				msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(dtoList.get(i).getEmail(), false));
				
				msg.setText(content);
				msg.setSentDate(new Date());
				Transport.send(msg);
				System.out.println("메일 전송 완료");
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}
	
		
	}
}
