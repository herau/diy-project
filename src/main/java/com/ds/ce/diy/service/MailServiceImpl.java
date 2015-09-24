package com.ds.ce.diy.service;

import com.ds.ce.diy.domain.User;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

//TODO make the service async
@Service
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;

    @Inject
    public MailServiceImpl(JavaMailSender mailSender) {
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
}
