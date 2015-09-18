package com.dassault_systemes.diy.service;

import com.dassault_systemes.diy.domain.User;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.inject.Inject;

/**
 * Created by n27 on 9/18/15.
 */
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;

    @Inject
    public MailServiceImpl(JavaMailSender mailSender) {

        this.mailSender = mailSender;
    }

    @Override
    public void sendMail(User user, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setText(content);
    }
}
