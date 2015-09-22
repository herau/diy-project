package com.dassault_systemes.diy.service;

import com.dassault_systemes.diy.domain.User;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

//TODO make the service async
@Service
public class MailServiceImpl implements MailService {

    private final MailSender mailSender;

    private final SimpleMailMessage template;

    @Inject
    public MailServiceImpl(MailSender mailSender, SimpleMailMessage template) {
        this.mailSender = mailSender;
        this.template = template;
    }

    @Override
    public void sendMail(User user, String content) {
        SimpleMailMessage message = new SimpleMailMessage(template);
        //TODO check that the sender name is correct according to configuraiton (spring.mail.properties)
        // -> otherwise use JavaMailSender instead of mailSender and maybe MimeMessage instead of SimpleMailMessage
        message.setTo(user.getEmail());
        message.setText(content);

        mailSender.send(message);
    }
}
