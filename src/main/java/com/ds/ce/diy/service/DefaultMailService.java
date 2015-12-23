package com.ds.ce.diy.service;

import com.ds.ce.diy.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.inject.Inject;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import java.io.IOException;
import java.util.Properties;

//TODO make the service async
@Service
public class DefaultMailService implements MailService {

    public static final Logger logger = LoggerFactory.getLogger(DefaultMailService.class);

    private final JavaMailSender mailSender;

    @Inject
    public DefaultMailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendMail(User user, String subject, String content) {
        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
            message.setTo(user.getEmail());
            message.setSubject(subject);
            message.setText(content, true);
        };

        mailSender.send(preparator);
    }

    @Override
    public void sendCalendar(User user, String subject, String calendar) {
        //FIXME normally no need to use message.setFrom()
        Properties mailProperties = ((JavaMailSenderImpl) mailSender).getJavaMailProperties();
        String from = mailProperties.getProperty("mail.smtp.from");
        String personal = mailProperties.getProperty("mail.smtp.user");

        try {
            // create and send the message
            MimeMessage message = new MimeMessage(((JavaMailSenderImpl) mailSender).getSession());
            message.addHeader("method", "REQUEST");
            message.addHeader("charset", "UTF-8");
            message.addHeader("component", "VEVENT");

            if (from != null && personal != null) {
                message.setFrom(new InternetAddress(from, personal));
            }

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
            message.setSubject(subject);

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setHeader("Content-Class", "urn:content-classes:calendarmessage");
            messageBodyPart.setHeader("Content-ID", "calendar_message");
            messageBodyPart.setDataHandler(new DataHandler(new ByteArrayDataSource(calendar, "text/calendar")));
            message.setContent(new MimeMultipart(messageBodyPart));

            mailSender.send(message);

        } catch (MessagingException | IOException e) {
            logger.warn("Unable to build the mail message: {}" + e.getMessage(), e);
        }
    }
}
