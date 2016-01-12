package com.ds.ce.diy.service;

import com.ds.ce.diy.domain.Company;
import com.ds.ce.diy.domain.State;
import com.ds.ce.diy.domain.User;
import net.fortuna.ical4j.model.Calendar;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class MailServiceTest {

    @Test
    @Ignore
    public void testSendInvitation() throws Exception {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.paris.exalead.com");
        mailSender.setDefaultEncoding(StandardCharsets.UTF_8.toString());
        Properties javaMailProperties = new Properties();
        javaMailProperties.setProperty("mail.smtp.from", "trer@3ds.com");
        javaMailProperties.setProperty("mail.smtp.user", "Sender");
        mailSender.setJavaMailProperties(javaMailProperties);
        DefaultMailService mailService = new DefaultMailService(mailSender);

        User dd = new User("dd", "", "", "", "n27@3ds.com", Company.DS);
        dd.setState(State.VALID);

        ArrayList<User> required = new ArrayList<>();
        required.add(dd);

        ICalendarService iCalendarService = new ICalendarService();
        ZoneId zoneId = ZoneId.of("Europe/Paris");

        LocalDate now = LocalDate.now();
        LocalDateTime localDateTime = ZonedDateTime.of(now.getYear(), now.getMonthValue(), now.getDayOfMonth(), 16, 0, 0, 0, zoneId).toLocalDateTime();
        List<Duration> alarms = new ArrayList<>();
        alarms.add(Duration.ofDays(-1));
        alarms.add(Duration.ofHours(-2));
        Calendar calendar = iCalendarService.getCalendar(required,
                                                         localDateTime,
                                                         Duration.ofHours(2), "location", "event summary", "desc",
                                                         "bricolage@ce-3ds.com", "category", Collections.emptyList(), alarms);

        mailService.sendCalendar(dd, "subject of the mail", calendar.toString());
    }
}
