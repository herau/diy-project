package com.dassault_systemes.diy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.time.LocalTime;
import java.util.Base64;
import java.util.UUID;

@Entity
@Table(name = "tokens")
public class VerificationToken {

    @OneToOne(optional = false)
    @JoinColumn(unique = true, nullable = false, updatable = false, name = "user_id")
    private final User user;

    @Column(nullable = false, updatable = false, name = "type")
    private final VerificationTokenType type;

    /**
     * time to live for the Token. According to configuration ${app.email.registration.expiryTime}
     */
    @Column(nullable = false, updatable = false, name = "expiry_date")
    private final LocalTime tokenExpiryDate;

    /**
     * a UUID that is used to identify the token. It is Base64 encoded before being sent
     */
    @Column(nullable = false, updatable = false)
    private final String token;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false, name = "token_id")
    private Integer id;

    /**
     * has this token been verified
     */
    @Column
    private boolean verified;

    public VerificationToken(User user, VerificationTokenType type, int emailRegistrationTokenExpiryTime) {
        this.user = user;
        this.type = type;
        this.tokenExpiryDate = LocalTime.now().plusMinutes(emailRegistrationTokenExpiryTime);
        this.token = Base64.getEncoder().encodeToString(UUID.randomUUID().toString().getBytes());
    }

    public String getToken() {
        return token;
    }
}
