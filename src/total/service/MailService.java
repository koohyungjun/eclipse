package total.service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	@Autowired
	JavaMailSender mailSender;

	public boolean sendWelcomeMail(String target) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			// 받을 사람 -
			message.setRecipient(RecipientType.TO, new InternetAddress(target));
			// 보내는 사람 - google 서버같은 경우는 이 설정을 무시함.
			message.setFrom(new InternetAddress("admin@spring.io"));
			// 제목
			message.setSubject("[SpringIO] 가입을 축하드립니다");
			// 내용
			String content = "가입을 <b>축하</b>드립니다.\n사용에 불편하신점이 있으면 고객센터에 글을 남겨주세요.\n";
			content +="<a href=\"http://192.168.10.73/emailauth\">사이트 둘러보기</a>";
			message.setContent(content, "text/html;charset=utf-8");
				// content 설정을 text/html;charset=utf-8 이라고 보내면.. HTML로 보낼수도 있다.
			
			mailSender.send(message);
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;  
		}
	}

}
