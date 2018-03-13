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
			// ���� ��� -
			message.setRecipient(RecipientType.TO, new InternetAddress(target));
			// ������ ��� - google �������� ���� �� ������ ������.
			message.setFrom(new InternetAddress("admin@spring.io"));
			// ����
			message.setSubject("[SpringIO] ������ ���ϵ帳�ϴ�");
			// ����
			String content = "������ <b>����</b>�帳�ϴ�.\n��뿡 �����Ͻ����� ������ �����Ϳ� ���� �����ּ���.\n";
			content +="<a href=\"http://192.168.10.73/emailauth\">����Ʈ �ѷ�����</a>";
			message.setContent(content, "text/html;charset=utf-8");
				// content ������ text/html;charset=utf-8 �̶�� ������.. HTML�� �������� �ִ�.
			
			mailSender.send(message);
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;  
		}
	}

}
