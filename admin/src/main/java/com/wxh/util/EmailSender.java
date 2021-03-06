package com.wxh.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EmailSender {
	public static void main(String[] args) throws Exception {
		sendEmail("502696250@qq.com","小华备忘录注册验证码","您好！您注册的验证码为3456，,有效期为30分钟，请及时使用！【小华备忘录】");
	}
	/**
	 * 邮箱发送模式01：纯文本格式
	 */
	public static void sendEmail(String receiver, String title, String body) throws Exception {
		Properties prop = new Properties();
		prop.setProperty("mail.host", EmailParam.emailHost);
		prop.setProperty("mail.transport.protocol", EmailParam.emailProtocol);
		prop.setProperty("mail.smtp.auth", EmailParam.emailAuth);
		//使用JavaMail发送邮件的5个步骤
		//1、创建session
		Session session = Session.getInstance(prop);
		//开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
		session.setDebug(true);
		//2、通过session得到transport对象
		Transport ts = session.getTransport();
		//3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
		ts.connect(EmailParam.emailHost, EmailParam.emailSender, EmailParam.password);
		//4、创建邮件
		Message message = createEmail01(session, receiver, title, body);
		//5、发送邮件
		ts.sendMessage(message, message.getAllRecipients());
		ts.close();
	}
	/**
	 * 创建文本邮件
	 */
	private static MimeMessage createEmail01(Session session, String receiver, String title, String body)
	throws Exception {
		//创建邮件对象
		MimeMessage message = new MimeMessage(session);
		//指明邮件的发件人
		String nick = javax.mail.internet.MimeUtility.encodeText(EmailParam.emailNick);
		message.setFrom(new InternetAddress(nick + "<" + EmailParam.emailSender + ">"));
		//指明邮件的收件人
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
		//邮件的标题
		message.setSubject(title);
		//邮件的文本内容
		message.setContent(body, "text/html;charset=UTF-8");
		//返回创建好的邮件对象
		return message;
	}
}
