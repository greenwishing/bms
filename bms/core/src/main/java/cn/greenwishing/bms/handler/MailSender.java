package cn.greenwishing.bms.handler;

import cn.greenwishing.bms.cache.ConfigurationCache;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * @author Frank wu
 * @date 2017/5/13
 */
public class MailSender {

    private static JavaMailSender sender = null;
    private static JavaMailSender get() {
        if (sender == null) {
            String username = ConfigurationCache.get("mail.username");
            String password = ConfigurationCache.get("mail.password");
            Integer port = ConfigurationCache.getInt("mail.port", 465);
            String encoding = ConfigurationCache.get("mail.encoding", "UTF-8");
            String auth = ConfigurationCache.get("mail.smtp.auth", "true");
            String socketFactoryClass = ConfigurationCache.get("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            String host = ConfigurationCache.get("mail.smtp.host", "smtp.exmail.qq.com");
            Integer smtpPort = ConfigurationCache.getInt("mail.smtp.port", 465);
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost(host);
            mailSender.setPort(port);
            mailSender.setUsername(username);
            mailSender.setPassword(password);
            mailSender.setDefaultEncoding(encoding);
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", auth);
            properties.put("mail.smtp.socketFactory.class", socketFactoryClass);
            properties.put("mail.smtp.port", smtpPort);
            properties.put("mail.smtp.socketFactory.port", smtpPort);
            mailSender.setJavaMailProperties(properties);
            sender = mailSender;
        }
        return sender;
    }

    public static void refresh() {
        sender = null;
    }

    public static void systemSend(String to, String subject, String content) {
        String from = ConfigurationCache.get("mail.default.sender", "bms@greenwishing.cn");
        send(from, to, subject, content);
    }

    private static void send(String from, String to, String subject, String content) {
        try {
            JavaMailSender mailSender = get();
            MimeMessageHelper helper = new MimeMessageHelper(mailSender.createMimeMessage());
            helper.setSubject(subject);
            helper.setText(content, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSentDate(new Date());
            mailSender.send(helper.getMimeMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
