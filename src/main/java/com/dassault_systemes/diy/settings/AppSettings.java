package com.dassault_systemes.diy.settings;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;

@Component
@ConfigurationProperties(prefix = "app")
public class AppSettings {

    private Email email;

    @PostConstruct
    public void init() {
        Assert.notNull(email);
        Assert.notNull(email.registration);
        Assert.notNull(email.registration.getTokenExpiryTime());
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public static class Email {

        private Registration registration;

        private String subject;

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public Registration getRegistration() {
            return registration;
        }

        public void setRegistration(Registration registration) {
            this.registration = registration;
        }

        public static class Registration {

            private int tokenExpiryTime;

            /**
             * @return Email registration Token Expiry Time in Minutes
             */
            public int getTokenExpiryTime() {
                return tokenExpiryTime;
            }

            public void setTokenExpiryTime(int tokenExpiryTime) {
                this.tokenExpiryTime = tokenExpiryTime;
            }
        }
    }

}
