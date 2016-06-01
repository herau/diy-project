package com.ds.ce.diy.settings;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;

@Component
@ConfigurationProperties(prefix = "app")
@Getter
@Setter
public class AppSettings {

    private Email email = new Email();

    @PostConstruct
    public void init() {
        Assert.notNull(email);
        Assert.notNull(email.registration);
        Assert.notNull(email.registration.getTokenExpiration());
    }

    @Getter
    @Setter
    public static class Email {

        private Registration registration = new Registration();

        @Getter
        @Setter
        public static class Registration {

            private int tokenExpiration;

            private String subject;
        }
    }

}
