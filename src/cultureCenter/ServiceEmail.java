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

import member.bean.MemberDTO;
import member.dao.MemberDAO;

@SuppressWarnings("all")
public class ServiceEmail {
	private String userid, useremail, content;

	public ServiceEmail(String id, String content) {
		this.userid = id;
		this.content = content;
		
		// MemberDTO
		MemberDTO dto = new MemberDTO();
		dto.setId(userid);

		// DB
		MemberDAO dao = MemberDAO.getInstance();

		dto = dao.myInfoArticle(dto);
		useremail = dto.getEmail();
		
		Properties props = System.getProperties();
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.port", "465");
		props.put("mail.smtp.auth", "true");		
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.trust", true);
		
		String email = "dewkim6604@gmail.com";
		String pass = "happy9247";
		
		Session session = Session.getDefaultInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(email, pass);
			}
		});
		
		//메세지
		Message msg = new MimeMessage(session);
		
		//
		try {
			msg.setSubject(userid+" 님의 문의 사항");
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
			
			msg.setText(content+"\n\n"+userid+"님의 메일 : "+useremail);
			msg.setSentDate(new Date());
			Transport.send(msg);
			System.out.println("메일 전송 완료");
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
	}
}
