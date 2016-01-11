package com.ds.ce.diy.domain;

import com.ds.ce.diy.domain.security.VerificationToken;
import org.junit.Test;

public class LombokEntitiesTest {

    @Test
    public void required_NoArgsConstructory_JPA() throws Exception {
        new Notification();
        new Accessory();
        new Lifespan();
        new Supply();
        new User();
        new Account();
        new Tool();
        new Tag();
        new VerificationToken();
        new Registration();
    }
}
