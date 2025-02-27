package org.task.taskserver.service;

import com.sun.mail.smtp.SMTPTransport;
import org.task.taskserver.domain.User;
import org.task.taskserver.domain.token.ConfirmationToken;
import org.task.taskserver.domain.token.ConfirmationToken;
import org.springframework.stereotype.Service;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import static javax.mail.Message.RecipientType.CC;
import static javax.mail.Message.RecipientType.TO;
import static org.task.taskserver.constant.EmailConstant.*;
import static org.task.taskserver.constant.EmailType.*;

@Service
public class EmailService {

    public void sendEmail(User user, String siteUrl, ConfirmationToken confirmationToken, int emailIdentifier, String randomPassword) throws MessagingException, IOException {
        Message message = createEmail(user, siteUrl, confirmationToken, emailIdentifier, randomPassword);
        SMTPTransport smtpTransport = (SMTPTransport) getEmailSession().getTransport(SIMPLE_MAIL_TRANSFER_PROTOCOL);
        smtpTransport.connect(OUTLOOK_SMTP_SERVER, USERNAME, PASSWORD);
        smtpTransport.sendMessage(message, message.getAllRecipients());
        smtpTransport.close();
    }

    private Message createEmail(User user,String siteUrl,ConfirmationToken confirmationToken, int emailIdentifier, String randomPassword) throws MessagingException,
            IOException {
        String verifyURL = "";
        Message message = new MimeMessage(getEmailSession());
        MimeMultipart content = new MimeMultipart("related");
        // ContentID is used by both parts
        String cid = ContentIdGenerator.getContentId();
        MimeBodyPart mainPart = new MimeBodyPart();
        if(emailIdentifier == VERIFICATION_EMAIL || emailIdentifier == VERIFICATION_EMAIL_WITH_PASSWORD || emailIdentifier == SEND_RESET_PASSWORD_EMAIL) {
            verifyURL = siteUrl + "/verify/" + confirmationToken.getToken();
        }
        if(emailIdentifier == VERIFICATION_EMAIL) {
            mainPart.setText("<div><img style=\"width:150px;margin-top:10px\" src=\"cid:" + cid + "\" /></div>\n" +
                    "Dear " + user.getFirstName() + ",<br>\n" +
                    "Please click the link below to verify your registration:<br>\n" +
                    "<h3><a href= " + verifyURL + " target=\"_self\">Activate Your Account</a></h3>\n" +
                    "Thank you,<br>\n" +
                    "Task", "US-ASCII", "html");
        }
        if(emailIdentifier == VERIFICATION_EMAIL_WITH_PASSWORD) {
            mainPart.setText("<div><img style=\"width:150px;margin-top:10px\" src=\"cid:" + cid + "\" /></div>\n" +
                    "Dear " + user.getFirstName() + ",<br>\n" +
                    "Your password is " + randomPassword + "<br>\n" +
                    "Please click first the link below to verify your registration:<br>\n" +
                    "<h3><a href= " + verifyURL + " target=\"_self\">Activate Your Account</a></h3>\n" +
                    "Thank you,<br>\n" +
                    "Task", "US-ASCII", "html");
        }
        if(emailIdentifier == SEND_RESET_PASSWORD_EMAIL) {
            mainPart.setText("<div><img style=\"width:150px;margin-top:10px\" src=\"cid:" + cid + "\" /></div>\n" +
                    "Dear " + user.getFirstName() + ",<br>\n" +
                    "Please click the link below to change your password:<br>\n" +
                    "<h3><a href= " + verifyURL + " target=\"_self\">New Password</a></h3>\n" +
                    "Thank you,<br>\n" +
                    "Task", "US-ASCII", "html");
        }
        if(emailIdentifier == REGISTER_EMAIL) {
        mainPart.setText("<section>\n" +
                " <div><img style=\"width:150px;margin-top:10px\" src=\"cid:" + cid + "\" /></div>\n" +
                " <p>Hello " + user.getFirstName() + ",</p>\n" +
                " <p>Welcome to Task! Thanks so much for joining our platform. We're looking forward to helping you with our products.</p>\n" +
                " <p>We offer lots of ways to connect:</p>\n" +
                " <ul class=\"default-list\">\n" +
                " <li>Follow us on social media [<u>Twitter</u>, <u>Facebook</u>, <u>Instagram</u>, <u>LinkedIn</u>]</li>\n" +
                " <li><u>Subscribe to our newsletter</u> for special offers and discounts</li>\n" +
                " <li>Watch our <u>YouTube videos</u> about [topic]</li>\n" +
                " </ul>\n" +
                " <p>We're here to help! If you have any questions, please reply to this email or call our customer service team at 4-555-555-555. We're available Monday through Friday, from 7 a.m. to 9 p.m. CST.</p>\n" +
                " <p>Sincerely,<br>Theodoros Avestas, customer service agent</p>\n" +
                " </section>", "US-ASCII", "html");
        }
        content.addBodyPart(mainPart);
        // Image part
        MimeBodyPart imagePart = new MimeBodyPart();
        imagePart.attachFile("src/task.jpg");
        imagePart.setContentID("<" + cid + ">");
        imagePart.setDisposition(MimeBodyPart.INLINE);
        content.addBodyPart(imagePart);
        message.setFrom(new InternetAddress(FROM_EMAIL));
        message.setRecipients(TO, InternetAddress.parse(user.getEmail(), false));
        message.setRecipients(CC, InternetAddress.parse(CC_EMAIL, false));
        message.setSubject(EMAIL_SUBJECT);
        message.setContent(content);
        message.setSentDate(new Date());
        message.saveChanges();
        return message;
    }

    private Session getEmailSession() {
        Properties properties = System.getProperties();
        properties.put(SMTP_HOST, OUTLOOK_SMTP_SERVER);
        properties.put(SMTP_AUTH, true);
        properties.put(SMTP_PORT, DEFAULT_PORT);
        properties.put(SMTP_STARTTLS_ENABLE, true);
        properties.put(SMTP_STARTTLS_REQUIRED, true);
        return Session.getInstance(properties, null);
    }
}
