package com.dassault_systemes.diy.settings;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public class AppSettings {

    public Email email;

    public static class Email {

        public Registration registration;

        public static class Registration {

            private int tokenExpiryTime;

            /**
             * @return Email registration Token Expiry Time in Minutes
             */
            public int getTokenExpiryTime() {
                return tokenExpiryTime;
            }
        }
    }
}
