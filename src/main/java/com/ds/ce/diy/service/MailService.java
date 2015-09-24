package com.ds.ce.diy.service;

import com.ds.ce.diy.domain.User;

/**
 * Created by n27 on 9/18/15.
 */
public interface MailService {

    void sendMail(User user, String subject, String content);

}
