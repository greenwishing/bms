package cn.greenwishing.bms.mail;

import cn.greenwishing.bms.handler.MailSender;
import org.testng.annotations.Test;

/**
 * User: Wufan
 * Date: 2017/5/13
 */
public class MailSendTest {

    @Test
    public void testMailSend() {
        MailSender.systemSend("1107006646@qq.com", "邮件主题【测试】", "邮件正文【测试】");
    }
}
