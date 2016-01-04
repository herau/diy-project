package com.ds.ce.diy.service;

import com.ds.ce.diy.domain.Company;
import com.ds.ce.diy.domain.State;
import com.ds.ce.diy.domain.User;
import net.fortuna.ical4j.model.Calendar;
import org.junit.Test;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Properties;

public class MailServiceTest {

    @Test
    public void testSendInvitation() throws Exception {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.paris.exalead.com");
        mailSender.setDefaultEncoding(StandardCharsets.UTF_8.toString());
        Properties javaMailProperties = new Properties();
        javaMailProperties.setProperty("mail.smtp.from", "n27@3ds.com");
        javaMailProperties.setProperty("mail.smtp.user", "Sender");
        mailSender.setJavaMailProperties(javaMailProperties);
        DefaultMailService mailService = new DefaultMailService(mailSender);

        User dd = new User("dd", "", "", "", "n27@3ds.com", Company.DS, State.VALID);

        ICalendarService iCalendarService = new ICalendarService();
        Calendar calendar = iCalendarService
                .getCalendar(Collections.singletonList(dd), Collections.emptyList(), LocalDateTime.now(), Duration.ofHours(2), "location", "event summary", "desc",
                             "category", "bricolage@ce-3ds.com");

        mailService.sendCalendar(dd, "subject of the mail", calendar.toString());

    }
}
