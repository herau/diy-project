package com.ds.ce.diy.domain;

import com.ds.ce.diy.domain.security.VerificationToken;
import org.junit.Test;

public class LombokEntitiesTest {

    @Test
    public void required_NoArgsConstructory_JPA() throws Exception {
        Notification notification = new Notification();
        Accessory accessory = new Accessory();
        Lifespan lifespan = new Lifespan();
        Supply supply = new Supply();
        User user = new User();
        Account account = new Account();
        Tool tool = new Tool();
        Tag tag = new Tag();
        VerificationToken verificationToken = new VerificationToken();
    }
}
