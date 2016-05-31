package com.ds.ce.diy.service;

import com.ds.ce.diy.domain.User;

interface MailService {

    void sendMail(User user, String subject, String content);

}
