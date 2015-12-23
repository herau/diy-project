package com.ds.ce.diy.service;

import com.ds.ce.diy.domain.User;

/**
 * Created by n27 on 9/18/15.
 */
public interface MailService {

    void sendMail(User user, String subject, String content);

    /**
     * send an invitation through the Icalendar format https://www.ietf.org/rfc/rfc2445.txt
     * TODO have to be invitation (with validation) or just automatic added entry in their calendar
     * TODO need participant plus optional ?
     * @param user the mail receiver
     * @param subject the mail subject
     * @param calendar String representation of an ICalendar
     */
    void sendCalendar(User user, String subject, String calendar);

}
