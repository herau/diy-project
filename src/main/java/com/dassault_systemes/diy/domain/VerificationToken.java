package com.dassault_systemes.diy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import java.time.LocalTime;
import java.util.Base64;
import java.util.UUID;

@Entity
@Table(name = "tokens", indexes = {@Index(columnList = "token", name = "token_value_hidx")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"user", "type"})})
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false, name = "token_id")
    private Integer id;

    @OneToOne(optional = false)
    @JoinColumn(nullable = false, updatable = false, name = "user_id")
    private User user;

    @Column(nullable = false, updatable = false, name = "type")
    @Enumerated
    private VerificationTokenType type;

    /**
     * time to live for the Token. According to configuration ${app.email.registration.expiryTime}
     */
    @Column(nullable = false, updatable = false, name = "expiry_date")
    private LocalTime expiryDate;

    /**
     * a UUID that is used to identify the token. It is Base64 encoded before being sent
     */
    @Column(nullable = false, updatable = false)
    private String token;

    /**
     * has this token been verified
     */
    @Column
    private boolean verified;

    public VerificationToken() {}

    public VerificationToken(User user, VerificationTokenType type, int emailRegistrationTokenExpiryTime) {
        this.user = user;
        this.type = type;
        this.expiryDate = LocalTime.now().plusMinutes(emailRegistrationTokenExpiryTime);
        this.token = UUID.randomUUID().toString();
    }

    /**
     * Verification token is only valid when it is not expired and hasn't been verified yet
     *
     * @return true is the verification token is valid, false otherwise
     *
     * @see VerificationToken#setVerified()
     */
    public boolean isValid() {
        return !verified && expiryDate.isAfter(LocalTime.now());
    }

    public void setVerified() {
        this.verified = true;
    }

    public String getToken() {
        return Base64.getEncoder().encodeToString(token.getBytes());
    }

    public User getUser() {
        return user;
    }

    public VerificationTokenType getType() {
        return type;
    }
}
