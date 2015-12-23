package com.ds.ce.diy.settings;

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
        Assert.notNull(email.registration.getTokenExpiration());
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public static class Email {

        private Registration registration;

        private Invitation invitation;

        public Registration getRegistration() {
            return registration;
        }

        public Invitation getInvitation() {
            return invitation;
        }

        public void setInvitation(Invitation invitation) {
            this.invitation = invitation;
        }

        public void setRegistration(Registration registration) {
            this.registration = registration;
        }

        public static class Registration {

            private int tokenExpiration;

            private String subject;

            /**
             * Email Subject
             * @return
             */
            public String getSubject() {
                return subject;
            }

            public void setSubject(String subject) {
                this.subject = subject;
            }

            /**
             * @return Email registration Token Expiry Time in Minutes
             */
            public int getTokenExpiration() {
                return tokenExpiration;
            }

            public void setTokenExpiration(int tokenExpiration) {
                this.tokenExpiration = tokenExpiration;
            }
        }

        public static class Invitation {

            private String subject;

            private String location;

            public String getSubject() {
                return subject;
            }

            public void setSubject(String subject) {
                this.subject = subject;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }
        }
    }

}
